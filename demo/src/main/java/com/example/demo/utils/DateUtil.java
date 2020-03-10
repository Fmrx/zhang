package com.example.demo.utils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DateUtil {

    /**
     * 获取今天的日期
     */
    public static void demo1() {
        LocalDate today = LocalDate.now();
        System.out.println(today);
    }

    /**
     * 获取年月日
     */
    public static void demo2() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        System.out.println("年" + year +",月" + month + ",日" + day);
    }

    /**
     * 获取特定日期
     */
    public static void demo3(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        System.out.println(localDate);
    }

    /**
     * 判断两个日期是否相等
     */
    public static void demo4() {
        LocalDate date1 = LocalDate.now();

        LocalDate date2 = LocalDate.of(2020, 2, 26);

        if(date1.equals(date2)) {
            System.out.println("相等");
        }else {
            System.out.println("不相等");
        }
    }

    /**
     * 检查周期性日期, 比如生日
     */
    public static void demo5() {
        LocalDate date1 = LocalDate.now();

        LocalDate date2 = LocalDate.of(2020, 2, 26);

        MonthDay monthDay1 = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());

        MonthDay monthDay2 =  MonthDay.from(date1);
        if(monthDay2.equals(monthDay1)) {
            System.out.println("日期相等");
        }else {
            System.out.println("日期不等");
        }
    }

    /**
     * 获取当前时间, 不包含日期
     */
    public static void demo6() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
    }

    /**
     * 获取之后/之前的时间
     */
    public static void demo7() {
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = time1.plusHours(3);
        System.out.println("3小时后的时间" + time2);
    }

    /**
     * 一周后的日期
     */
    public static void demo8() {
        LocalDate localDate = LocalDate.now();
        LocalDate nextWeek = localDate.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期" + nextWeek);
    }

    public static void demo9() {
        LocalDate localDate = LocalDate.now();
        LocalDate lastYear = localDate.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期:" + lastYear);
        LocalDate nextYear = localDate.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期:" + nextYear);
    }

    public static void demo10() {
        Clock clock = Clock.systemUTC();
        Clock clock1 = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println(clock1.millis());
    }

    /**
     * 判断当前日期大于或小于某天
     */
    public static void demo11() {
        LocalDate today = LocalDate.now();

        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);

        if(tomorrow.isAfter(today)) {
            System.out.println("明天" + tomorrow);
        }

        if(yesterday.isBefore(today)) {
            System.out.println("前天" + yesterday);
        }
    }

    /**
     * 阿拉伯
     */
    public static final List<String> arabicAreas = Arrays.asList(
            "DZ", "BH", "TD", "KM", "DJ", "EG", "ER", "IQ", "JO", "KW", "LB", "LY", "MR", "MA", "OM", "PS", "QA", "SA", "SO", "SD", "SY", "TZ", "TN", "AE", "YE"
    );

    /**
     * 中国
     */
    public static final List<String> chinaAreas = Arrays.asList(
            "CN", "TW", "HK", "MO", "ZH"
    );

    /**
     * 全球
     */
    public static final List<String> globalAreas = Arrays.asList(
            "US", "GB", "CA", "AU", "RU", "MX", "FR", "IT", "GLOBAL"
    );

    //特殊机器人开放地区
    public static final List<String> SPECIAL_AREA = Arrays.asList(
            "IN","ID"
    );


    public static void main(String[] args) {
        demo1();
        demo2();
        demo3(2020, 2, 26);
        demo4();
        demo5();
        demo6();
        demo7();
        demo8();
        demo9();
        demo10();
        demo11();
        List<Long> list = new ArrayList<Long>();
        List<Long> list1 = new ArrayList<>();
        list.add(1l);
        list.add(2l);
        list.add(3l);
        list.add(4l);

        list1.add(4l);
        list1.add(3l);
        list1.add(5l);
        list1.retainAll(list);
        System.out.println(list1.toString());
    }
}
