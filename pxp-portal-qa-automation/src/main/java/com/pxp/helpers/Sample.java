// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.helpers;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.*;
import com.amazonaws.services.logs.AWSLogs;
import com.amazonaws.services.logs.AWSLogsClientBuilder;
import com.amazonaws.services.logs.model.GetLogEventsRequest;
import com.amazonaws.services.logs.model.GetLogEventsResult;
import com.amazonaws.services.logs.model.OutputLogEvent;
import com.pxp.config.SQSConfig;
import com.pxp.model.BaseRest;
import com.pxp.objectmaps.PPMainPage;
import com.pxp.producer.HREventQueueProducer;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;


public class Sample extends BaseRest {

    public void sqs_ecs_logs_workflow(String requestId, String event, String practiceId) throws InterruptedException {
        String taskSuffix = null;
        String containerInstanceArn = null;

        //producer

        HREventQueueProducer producer = new HREventQueueProducer(new SQSConfig().amazonSQSAsync());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("requestId", requestId);
        jsonObject.put("event", event);
        jsonObject.put("practiceId", practiceId);

        String jsonString = jsonObject.toString();

        logStep("Status of SQS = " + producer.send(jsonString));

        Thread.sleep(5000);

        //ECS

        AmazonECS ECSclient = AmazonECSClientBuilder.standard().build();

        ListTasksRequest request = new ListTasksRequest().withCluster(testData.getProperty("clusterName"))
                .withServiceName(testData.getProperty("pp_hr_event_serviceName"));
        ListTasksResult response = ECSclient.listTasks(request);
        int i = 0;

        logStep(response.getSdkResponseMetadata().toString());

        logStep("Size=" + response.getTaskArns().size());

        List<String> list = new ArrayList<String>(response.getTaskArns());
        taskSuffix = list.get(list.size()-1);

        //taskSuffix = "arn:aws:ecs:us-east-2:088274918562:task/dev-pp-ecs/7e4f4d36961a4a3eaf7d2bab3ae76eb0";

        logStep(taskSuffix);

        assert taskSuffix != null;

        String res = taskSuffix.substring(taskSuffix.length()-32);

        //Cloudwatch logs

        AWSLogsClientBuilder builder = AWSLogsClientBuilder.standard();

        AWSLogs logsClient = builder
                .build();

        GetLogEventsRequest eventRequest = new GetLogEventsRequest().withLogGroupName(testData.getProperty("pp_hr_event_groupName"));

        logStep("LogStreamName = " + testData.getProperty("pp_hr_event_LogStreamName") + res);

        GetLogEventsResult eventResponse = logsClient.getLogEvents(eventRequest.withLogStreamName(testData.getProperty("pp_hr_event_LogStreamName") + res));

        List<OutputLogEvent> collect = new ArrayList<OutputLogEvent>(eventResponse.getEvents());

        List<OutputLogEvent> finalCollect = collect.stream().filter(s -> s.getMessage().contains("RequestId= " + requestId))
                .collect(Collectors.toList());

        assert finalCollect.size() != 0;

        finalCollect.forEach(s -> System.out.println(s.getMessage()));

        logStep("pp-hr-event service successfully consuming data!!");

    }
}
