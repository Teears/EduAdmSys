package com.qifangli.edumanage.util;

public class WeekTimeUtil {

    public static String convertTime(String time){
        String result = "";
        switch (time){
            case "0":
                result="上午第1,2节";
                break;
            case "1":
                result="上午第3,4节";
                break;
            case "3":
                result="下午第1,2节";
                break;
            case "4":
                result="下午第3,4节";
                break;
            case "6":
                result="晚上第1,2节";
                break;
            default:
        }
        return result;
    }

    public static String convertWeek(String week){
        String result = "";
        switch (week){
            case "Mon":
                week="周一";
                break;
            case "Tues":
                week="周二";
                break;
            case "Wed":
                week="周三";
                break;
            case "Thur":
                week="周四";
                break;
            case "Fri":
                week="周五";
                break;
            default:
        }
        return result;
    }
}
