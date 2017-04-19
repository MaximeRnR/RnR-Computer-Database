package com.excilys.formation.util;

import java.util.concurrent.atomic.AtomicInteger;
import com.excilys.formation.service.ComputerService;



public enum CountTotal {
    INSTANCE;
    private AtomicInteger countTotal = new AtomicInteger(0);

    /**
     */
    CountTotal() {
        countTotal.set(ComputerService.INSTANCE.getCountOfAllComputersFromDB());
    }

    public int getCountTotal() {
        return countTotal.intValue();
    }
    /**
     */
    public void increaseCountTotal() {
        countTotal.incrementAndGet();
    }
    /**
     */
    public void decreaseCountTotal() {
        countTotal.decrementAndGet();
    }
    /**
     * @param count count
     */
    public void setCountTotal(int count) {
        countTotal.set(count);
    }
}
