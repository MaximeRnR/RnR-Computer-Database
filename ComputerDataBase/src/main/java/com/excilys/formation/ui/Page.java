package com.excilys.formation.ui;


//Class Pagination 
public enum Page {
    PAGE;
    public static int MAX_NUMBER_OF_OBJECT = 10;
    private int index;

    private Page() {
        this.index=0;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void next() {
        this.index=this.index+1;
    }

    public void previous() {

        this.index=this.index-1;

    }
    public static void setMAX_NUMBER_OF_OBJECT(int mAX_NUMBER_OF_OBJECT) {
        MAX_NUMBER_OF_OBJECT = mAX_NUMBER_OF_OBJECT;
    }

}
