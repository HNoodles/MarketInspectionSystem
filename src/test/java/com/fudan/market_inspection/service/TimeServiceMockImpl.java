package com.fudan.market_inspection.service;

import java.util.Calendar;
import java.util.Date;

public class TimeServiceMockImpl implements TimeService {
    final Date initDate;
    Date currentDate;

    TimeServiceMockImpl() {
        initDate = new Date();
        currentDate = initDate;
    }

    @Override
    public Date getCurrentDate() {
        return currentDate;
    }

    Date getDateOfNDaysLater(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initDate);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    Date setCurrentDateLaterByNDays(int n) {
        currentDate = getDateOfNDaysLater(n);
        return currentDate;
    }
}
