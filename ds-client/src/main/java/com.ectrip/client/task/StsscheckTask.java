package com.ectrip.client.task;

import com.ectrip.api.model.Stsschecktab;
import com.ectrip.api.service.ApiStsscheckService;
import com.ectrip.client.dao.StsschecktabMapper;
import com.ectrip.client.utils.CollectionUtil;
import com.ectrip.client.utils.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Properties;

/**
 * Created by huangxinguang on 2017/7/25 下午2:08.
 * 定时触发类
 * 多线程批量更新，每个线程commit 500条数据
 */
public class StsscheckTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(StsscheckTask.class);
    @Autowired
    private StsschecktabMapper stsschecktabMapper;

    @Autowired
    private ApiStsscheckService dataService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    /**
     * 服务商id
     */
    private static Integer PROVIDER_ID;
    /**
     * 当前日期
     */
    private static String CURRENT_DATE;
    /**
     * 每个线程分配的任务数量
     */
    private static final int COMMIT_NUM = 500;

    /**
     * 读取配置参数
     */
    static {
        try {
            Properties properties = PropsUtil.loadProps("data-sync.properties");
            PROVIDER_ID = PropsUtil.getInt(properties, "providerId");
            CURRENT_DATE = PropsUtil.getString(properties, "currentDate");
        }catch (Exception e) {
            LOGGER.error("读取同步文件异常，请检查！");
        }
    }

    /**
     * 定时执行任务
     */
    public void syncDataAndSave() {
        long start = System.currentTimeMillis();
        LOGGER.info("同步数据开始...");
        queryAndSaveSyncData();
        long end = System.currentTimeMillis();
        LOGGER.info("同步数据结束...花费时间：{} 毫秒",(end-start));
    }

    /**
     * 查询中心服务器数据，保存到本地，并更新中心和本地同步状态：
     * 多线程批量更新，每个线程commit 500条数据
     */
    @Transactional
    public void queryAndSaveSyncData() {
        try {
            LOGGER.info("查询参数:{},{}",PROVIDER_ID,CURRENT_DATE);
            List<Stsschecktab> stsscheckList = dataService.queryAllNoSyncStsscheckList(PROVIDER_ID,CURRENT_DATE);//中心服务器数据
            LOGGER.info("查询数据条数:{}",CollectionUtils.isEmpty(stsscheckList) ? 0 : stsscheckList.size());

            //保存数据并更新同步状态
            if (!CollectionUtils.isEmpty(stsscheckList)) {
                for(Stsschecktab stsschecktab : stsscheckList) {//同步状态都设为1
                    stsschecktab.setSyncstatus(1);
                }

                int threadCount;
                if(stsscheckList.size() % COMMIT_NUM == 0) {
                    threadCount = stsscheckList.size() / COMMIT_NUM;
                }else {
                    threadCount = stsscheckList.size() / COMMIT_NUM + 1;//分配几个线程，单个线程处理500个
                }

                LOGGER.info("分配线程数：{}",threadCount);

                List<List<Stsschecktab>> unitlists = CollectionUtil.subList(stsscheckList,COMMIT_NUM);//均等分割
                for(int index = 0;index < threadCount;index++) {
                    final List<Stsschecktab> unitList = unitlists.get(index);
                    threadPoolTaskExecutor.execute(new Runnable() {
                        public void run() {
                            stsschecktabMapper.batchMerge(unitList);
                            dataService.batchUpdateSyncStatus(unitList);
                        }
                    });
                }
                LOGGER.info("批量更新合并数据成功！");
            }
        }catch (Exception e) {
            LOGGER.error("定时同步出错了！",e);
        }

    }
}
