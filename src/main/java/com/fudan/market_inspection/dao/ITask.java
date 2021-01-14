package com.fudan.market_inspection.dao;

import java.util.Date;

public interface ITask {
    boolean markFinish(Date finishDate);

    boolean isFinished();

    Date getFinishDate();
}
