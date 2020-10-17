package com.atguigu;


import com.rfidread.Enumeration.eGPO;
import com.rfidread.Enumeration.eGPOState;
import com.rfidread.Enumeration.eReadType;
import com.rfidread.RFIDReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(HelloWorldMainApplication.class,args);

        IMessageHandler iAsynchronousMessage = new IMessageHandler();
        String ConnID =  "192.168.10.91:9090";
        if (RFIDReader.CreateTcpConn(ConnID, iAsynchronousMessage)) {
            // 开启连接后重新开始读取标签数据
            RFIDReader._Config.Stop(ConnID);
            RFIDReader._Tag6C.GetEPC_TID_UserData(ConnID,1, eReadType.Inventory, 0, 32);
            //，开启机器指示灯
            HashMap<eGPO, eGPOState> dicState = new HashMap();
            dicState.put(eGPO._1, eGPOState._High);
            if (RFIDReader._Config.SetReaderGPOState(ConnID, dicState) != 0) {
                System.out.println("配置失败!");
            } else {
                System.out.println("配置成功!");
            }

            HashMap<Integer, Integer> dicPower = new HashMap<Integer, Integer>();
            dicPower.put(1, 8);
            if(RFIDReader._Config. SetANTPowerParam (ConnID, dicPower) != 0){
                System.out.println("功率设置失败！");
            }else{
                System.out.println("功率设置成功！");
            }

            System.out.println("创建连接成功！");
        } else {
            System.out.println("创建连接失败！");
        }
    }
}
