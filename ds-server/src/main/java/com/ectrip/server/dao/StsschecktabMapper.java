package com.ectrip.server.dao;



import com.ectrip.api.model.Stsschecktab;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StsschecktabMapper {

    /**
     * 查询所有未同步数据列表
     * @return
     */
    List<Stsschecktab> queryAllNoSyncStsscheckList(@Param("providerId") Integer providerId,@Param("currentDate") String currentDate);

    /**
     * 批量更新同步状态
     * @param list id列表
     * @return
     */
    int batchUpdateSyncStatus(List<Stsschecktab> list);
}