package com.ectrip.api.service;



import com.ectrip.api.model.Stsschecktab;

import java.util.List;

/**
 * Created by huangxinguang on 2017/7/25 上午11:02.
 * 服务端操作service
 */
public interface ApiStsscheckService {
    /**
     * 查询服务端所有未同步数据
     * @param providerId 服务商id
     * @param currentDate 当前时间
     * @return
     */
    List<Stsschecktab> queryAllNoSyncStsscheckList(Integer providerId,String currentDate);

    /**
     * 批量更新同步状态
     * @param list
     */
    void batchUpdateSyncStatus(List<Stsschecktab> list);
}
