package com.qifangli.edumanage.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class Duration5Util {
    public static boolean isTimeOut(LocalDateTime startTime){
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime,endTime );
        if(duration.toMinutes()>10){
            return true;
        }else {
            return false;
        }
    }
}
