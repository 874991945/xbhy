package com.czj.scheduled;

import com.czj.service.MeetingService;

import java.util.TimerTask;


public class MeetingTask extends TimerTask {

    private boolean isRunning = false;

    private MeetingService meetingService = new MeetingService();

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            //开始执行
            meetingService.updateStatusTask();
            //执行结束
            isRunning = false;
        }
    }

}
