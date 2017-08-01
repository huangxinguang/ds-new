package com.ectrip.server;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ectrip.api.model.Stsschecktab;
import com.ectrip.api.service.ApiStsscheckService;

import java.util.List;

/**
 * Created by huangxinguang on 2017/7/27 下午3:44.
 */
public class StsscheckTest {
    public static void main(String[] args) {
        //不使用Spring服务器访问地址
        //String url="http://localhost:8080/HessianServer/us";
        //使用spring服务器访问的地址
        String url = "http://localhost:8080/hessian/dataService";
        //获得HessianProxyFactory实例
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            ApiStsscheckService stsscheckService = (ApiStsscheckService) factory.create(ApiStsscheckService.class, url);
            //执行服务端方法
            List<Stsschecktab> list = stsscheckService.queryAllNoSyncStsscheckList(1,"2017-08-01");
            //遍历输出
            System.out.println("数据条数：" + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
