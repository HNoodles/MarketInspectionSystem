package com.fudan.market_inspection.service;

import java.util.Date;

public interface TimeService {
    /**
     * This method should return the current date of the system.
     * @return current date of the system
     */
    Date getCurrentDate();

    /**
     * This method should return a Date object representing the indicated date.
     * @param n the indicated num of days that is later than the current date
     * @return the indicated date
     */
    Date getNDaysLater(int n);
}
