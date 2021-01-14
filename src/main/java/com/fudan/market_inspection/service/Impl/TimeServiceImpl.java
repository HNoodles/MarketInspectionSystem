package com.fudan.market_inspection.service.Impl;

import com.fudan.market_inspection.service.TimeService;

import java.util.Calendar;
import java.util.Date;

public class TimeServiceImpl implements TimeService {
    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
