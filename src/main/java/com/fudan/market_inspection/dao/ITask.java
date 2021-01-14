package com.fudan.market_inspection.dao;

import java.util.Date;

public interface ITask {
    public boolean markFinish(Date finishDate);

    public boolean isFinished();

    public Date getFinishDate();
}
