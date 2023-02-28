package com.auto17.dataVisualization.api;

import com.auto17.dataVisualization.iot.IotTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * dashboard
 *
 * @author jianxiang sun
 */

@Controller
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    private SimpMessagingTemplate simpMessagingTemplate;

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    public ApiController(SimpMessagingTemplate template) {
        this.simpMessagingTemplate = template;
        IotTask.messagingTemplate=template;
        logger.info("****ApiController****");
    }

}
