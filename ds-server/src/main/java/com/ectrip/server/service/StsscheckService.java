package com.ectrip.server.service;

import com.ectrip.api.model.Stsschecktab;
import com.ectrip.api.service.ApiStsscheckService;
import com.ectrip.server.dao.StsschecktabMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangxinguang on 2017/7/25 下午1:53.
 */
@Service
public class StsscheckService implements ApiStsscheckService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StsscheckService.class);

    @Autowired
    private StsschecktabMapper stsschecktabMapper;

    public List<Stsschecktab> queryAllNoSyncStsscheckList(Integer providerId,String currentDate) {
        List<Stsschecktab> list = null;
        try {
            list = stsschecktabMapper.queryAllNoSyncStsscheckList(providerId,currentDate);
        }catch (Exception e) {
            LOGGER.error("查询数据失败！",e);
        }
        return list;
    }

    public void batchUpdateSyncStatus(List<Stsschecktab> list) {
        try {
            stsschecktabMapper.batchUpdateSyncStatus(list);
        }catch (Exception e) {
            LOGGER.error("批量更新同步状态失败！",e);
        }
    }
}
