package com.auto17.dataVisualization.iot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;


public class IotTask2 {
    public static SimpMessagingTemplate messagingTemplate=null;

    private Random random = new Random();
    private int index;

    //Execute every X seconds
    @Scheduled(fixedRate = 1000 * 4)
    public void IoT1(){
        String iotName="iot1";
        index = random.nextInt(60)%(61) + 1;
        System.out.println("服务端向客户端1发送消息:"+ System.currentTimeMillis());

        if(messagingTemplate!=null){
            messagingTemplate.convertAndSend(
                    "/data/iot1", ioTData("Temperature Gauge 1",index));
        }else {
            System.out.println("messagingTemplate is null");
        }
    }

    //Execute every X seconds
    @Scheduled(fixedRate = 1000 * 5)
    public void IoT2(){

        index = random.nextInt(60)%(61) + 1;
        System.out.println("服务端向客户端2发送消息:"+ System.currentTimeMillis());
        System.out.println("IoT2");
        System.out.println(ioTData("Temperature Gauge 2",index));
        if(messagingTemplate!=null){
            messagingTemplate.convertAndSend(
                    "/data/iot2", ioTData("Temperature Gauge 2",index));
        }else {
            System.out.println("messagingTemplate is null");
        }
    }

    private String ioTData(String name,int value) {

        JSONArray array=new JSONArray();
        JSONObject data1=new JSONObject();
        JSONArray arrayNode1=new JSONArray();
        JSONObject Node1=new JSONObject();
        data1.put("name",name);
        data1.put("value",value);
        arrayNode1.add(data1);
        Node1.put("data",arrayNode1);
        array.add(Node1);

        JSONObject data2=new JSONObject();
        JSONArray arrayNode2=new JSONArray();
        JSONObject Node2=new JSONObject();
        data2.put("name",name);
        data2.put("value",value);
        arrayNode2.add(data2);
        Node2.put("data",arrayNode2);
        array.add(Node2);


        JSONObject ioTData=new JSONObject();
        ioTData.put("series",array);
        System.out.println("********");
        System.out.println(ioTData.toJSONString());
        System.out.println("********");
        return ioTData.toJSONString();
    }

    public static void main(String[] sdf){
        IotTask2 task=new IotTask2();
        task.ioTData("xxx",90);

    }

}
