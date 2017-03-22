package com.excilys.formation.ui;

//Class Pagination
public enum Page {
    PAGE;
    /**
     */
    public static int mAXNUMBEROFOBJECTS = 10;
    private int index;

    /**
     */
    Page() {
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

    /**
     */
    public void next() {
        this.index = this.index + 1;
    }

    /**
     */
    public void previous() {

        this.index = this.index - 1;

    }

    /**
     * @param mAXNUMBEROFOBJECT max_num
     */
    public static void setMAXNUMBEROFOBJECTS(int mAXNUMBEROFOBJECT) {
        mAXNUMBEROFOBJECTS = mAXNUMBEROFOBJECT;
    }

}
