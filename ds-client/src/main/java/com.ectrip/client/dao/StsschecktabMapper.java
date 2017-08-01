package com.ectrip.client.dao;

import com.ectrip.api.model.Stsschecktab;

import java.util.List;

public interface StsschecktabMapper {
    /**
     *  批量保存
     * @param stsschecktabList
     * @return
     */
    int batchSave(List<Stsschecktab> stsschecktabList);

    /**
     * 批量合并
     * @param stsschecktabList
     * @return
     */
    int batchMerge(List<Stsschecktab> stsschecktabList);

}