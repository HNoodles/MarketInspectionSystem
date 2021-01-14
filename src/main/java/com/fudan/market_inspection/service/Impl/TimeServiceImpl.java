package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.service.TimeService;

import java.util.Calendar;
import java.util.Date;

public class TimeServiceImpl implements TimeService {
//    private final Date currentDate;
    //todo
//    private static TimeService timeService;
//
//    public static TimeService getInstance(Date currentDate) {
//        if (timeService == null) {
//            timeService = new TimeServiceImpl(currentDate);
//        }
//        return timeService;
//    }

//    public TimeServiceImpl() {
//    }

    @Override
    public Date getCurrentDate() {
        return new Date();
    }

    @Override
    public Date getNDaysLater(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCurrentDate());
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }
}
