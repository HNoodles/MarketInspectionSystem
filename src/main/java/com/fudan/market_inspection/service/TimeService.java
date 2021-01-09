package com.fudan.market_inspection.service;

import java.util.Date;

public interface TimeService {
    /**
     * This method should return the current date of the system.
     * @return current date of the system
     */
    Date getCurrentDate();

//    /**
//     * This method should return a date which is "days" after today.
//     * @param days the time period between the result date and current date of the system
//     * @return a date which is days after current date in the system
//     */
//    Date getDaysAfterToday(int days);
}
