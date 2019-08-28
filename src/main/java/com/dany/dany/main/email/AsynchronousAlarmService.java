package com.dany.dany.main.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class AsynchronousAlarmService {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    TaskExecutor taskExecutor;

    public void executeAsynchronously() {
        AlarmThread myThread = applicationContext.getBean(AlarmThread.class);
        taskExecutor.execute(myThread);
    }
}
