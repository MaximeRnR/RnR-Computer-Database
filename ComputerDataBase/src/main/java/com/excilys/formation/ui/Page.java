package com.excilys.formation.ui;

//Class Pagination
public class Page {
    /**
     */
    public int maxNumberOfObject = 10;
    private int index;

    /**
     */
    public Page() {
        this.index = 0;
    }

    /**
     * @return index index
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * @param index index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public int getMaxNumberOfObject() {
        return maxNumberOfObject;
    }

    /**
     * @param maxNumberOfObject max_num
     */

    public void setMaxNumberOfObject(int maxNumberOfObject) {
        this.maxNumberOfObject = maxNumberOfObject;
    }


}
