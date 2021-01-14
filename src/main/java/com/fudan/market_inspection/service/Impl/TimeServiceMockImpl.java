package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.service.TimeService;

import java.util.Calendar;
import java.util.Date;

public class TimeServiceMockImpl implements TimeService {
    private final Date currentDate;

    public TimeServiceMockImpl(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public Date getCurrentDate() {
        return currentDate;
    }

    @Override
    public Date getNDaysLater(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }
}
