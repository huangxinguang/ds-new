package com.ectrip.client.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxinguang on 2017/8/1 下午2:00.
 * 集合工具类
 */
public class CollectionUtil {
    /**
     * 把大List均等分割，多余的是一份
     * @param list
     * @param blockSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> subList(List<T> list, int blockSize) {
        List<List<T>> lists = new ArrayList<List<T>>();
        if (list != null && blockSize > 0) {
            int listSize = list.size();
            if(listSize <= blockSize){
                lists.add(list);
                return lists;
            }
            int batchSize = listSize / blockSize;
            int remain = listSize % blockSize;
            for (int i = 0; i < batchSize; i++) {
                int fromIndex = i * blockSize;
                int toIndex = fromIndex + blockSize;
                lists.add(list.subList(fromIndex, toIndex));
            }
            if(remain>0){
                lists.add(list.subList(listSize-remain, listSize));
            }
        }
        return lists;
    }
}
