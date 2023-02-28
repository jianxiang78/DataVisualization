package com.auto17.dataVisualization.simulator;

import com.auto17.dataVisualization.DTO.Iot;
import com.auto17.dataVisualization.grid.dbConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Simulate data sent by Internet of Things devices
 */

@Component
public class TaskSimulate {

    private final Logger logger = LoggerFactory.getLogger(TaskSimulate.class);
    private Random random = new Random();
    private int index;
    private Iot iot;

    @Scheduled(cron ="*/3 * * * * ?")
    public void simulator1() {
        String iotName="iot1";
        index = random.nextInt(60)%(61) + 1;
        iot=new Iot();
        iot.setName(iotName);
        iot.setValue(index);
        dbConnect.put(iotName,iot);
        logger.info(iotName+" push data to GridDB*******");
    }


    @Scheduled(cron ="*/5 * * * * ?")
    public void simulator2() {
        String iotName="iot2";
        index = random.nextInt(50)%(55) + 1;
        iot=new Iot();
        iot.setName(iotName);
        iot.setValue(index);
        dbConnect.put(iotName,iot);
        logger.info(iotName+" push data to GridDB*******");
    }
}
