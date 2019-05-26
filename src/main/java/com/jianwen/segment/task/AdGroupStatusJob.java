package com.jianwen.segment.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jianwen
 * @since 2019/03/31
 */
@Component
@EnableScheduling
public class AdGroupStatusJob {
    int i = 0;

    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void persistDaliyIntelligenceData() throws Exception {
        System.out.println("进入测试");
        if (i++ % 3 == 0){
            throw new Exception("我错了");
        }
    }
}
