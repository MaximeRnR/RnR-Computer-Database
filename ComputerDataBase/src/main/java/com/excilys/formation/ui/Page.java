package com.excilys.formation.ui;

import com.excilys.formation.service.ComputerService;

//Class Pagination 
public enum Page {
    PAGE;
    public static int MAX_NUMBER_OF_OBJECT = 10;
    private int index = 0;

    private Page() {
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void next() {
        this.index++;
    }

    public void previous() {

        this.index--;

    }

}
