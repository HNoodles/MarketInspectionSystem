package com.fudan.market_inspection.service.visitor;

import com.fudan.market_inspection.dao.CheckTask;

public abstract class AbstractVisitor {
    abstract public void visit(CheckTask checkTask);
}
