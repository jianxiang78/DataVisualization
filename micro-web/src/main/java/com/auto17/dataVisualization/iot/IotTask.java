package com.auto17.dataVisualization.iot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auto17.dataVisualization.DTO.Iot;
import com.auto17.dataVisualization.grid.dbConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IotTask {
    public static SimpMessagingTemplate messagingTemplate=null;

    private final Logger logger = LoggerFactory.getLogger(IotTask.class);
    private int index;
    private Iot iot;

    //Execute every X seconds
    @Scheduled(fixedRate = 1000 * 4)
    public void IoT1(){
        String iotName="iot1";
        iot = dbConnect.queryTopOne(iotName);
        if(iot==null){
            return;
        }
        index = iot.getValue();
        logger.info("The server sends a message to the client ["+iotName+"]:");

        if(messagingTemplate!=null){
            messagingTemplate.convertAndSend(
                    "/data/"+iotName, ioTData("Temperature Gauge 1",index));
        }else {
            logger.info(iotName+" messagingTemplate is null");
        }
    }

    //Execute every X seconds
    @Scheduled(fixedRate = 1000 * 5)
    public void IoT2(){

        String iotName="iot2";
        iot = dbConnect.queryTopOne(iotName);
        if(iot==null){
            return;
        }
        index = iot.getValue();
        logger.info("The server sends a message to the client ["+iotName+"]:");
        if(messagingTemplate!=null){
            messagingTemplate.convertAndSend(
                    "/data/"+iotName, ioTData("Temperature Gauge 2",index));
        }else {
            logger.info(iotName+" messagingTemplate is null");
        }
    }

    private String ioTData(String name,int value) {

        JSONArray arrayRoot=new JSONArray();
        JSONObject data1=new JSONObject();
        JSONArray arrayNode1=new JSONArray();
        JSONObject Node1=new JSONObject();
        data1.put("name",name);
        data1.put("value",value);
        arrayNode1.add(data1);
        Node1.put("data",arrayNode1);
        arrayRoot.add(Node1);

        JSONObject data2=new JSONObject();
        JSONArray arrayNode2=new JSONArray();
        JSONObject Node2=new JSONObject();
        data2.put("name",name);
        data2.put("value",value);
        arrayNode2.add(data2);
        Node2.put("data",arrayNode2);
        arrayRoot.add(Node2);


        JSONObject ioTData=new JSONObject();
        ioTData.put("series",arrayRoot);
        System.out.println("********");
        System.out.println(ioTData.toJSONString());
        System.out.println("********");
        return ioTData.toJSONString();
    }

    public static void main(String[] sdf){
        IotTask task=new IotTask();
        task.ioTData("xxx",90);

    }

}
