package com.excilys.formation.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class PageTag extends SimpleTagSupport {
    private int nbPage;
    private int index;
    private String reSearch;
    private String by;
    private int maxObj;

    /**
     * @param nbPage nePage
     */
    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }
    /**
     * @param maxObj maxObj
     */
    public void setMaxObj(int maxObj) {
        this.maxObj = maxObj;
    }

    /**
     * @param search Search
     */
    public void setReSearch(String search) {
        this.reSearch = search;
    }

    /**
     * @param by By
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * @param index index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @throws IOException ioexc
     * @throws JspException jsqpexc
     */
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String page = "";
        if (index != 0) {
            page = page + "<li id='previous'><a href='?search=" + reSearch + "&by=" + by + "&maxObj=" + maxObj + "&page=" + index + "' aria-label='Previous'>" + " <span aria-hidden='true'"
                    + ">&laquo;</span>" + "</a></li>";
        }
        for (int i = 0; i < nbPage + 1; i++) {

            if (i > index - 3 && i < index + 3) {
                if (i == index) {
                    page = page + "<li class='pages active'><a href='?search=" + reSearch + "&by=" + by + "&maxObj=" + maxObj + "&page=" + (i + 1) + "'>" + (i + 1)
                            + "</a></li>";
                } else {
                    page = page + "<li class='pages'><a href='?search=" + reSearch + "&by=" + by + "&maxObj=" + maxObj + "&page=" + (i + 1) + "'>" + (i + 1)
                            + "</a></li>";
                }
            }
        }

        if (index != nbPage) {
            page = page + "<li id='next'><a href='?search=" + reSearch + "&by=" + by + "&maxObj=" + maxObj + "&page=" + (index + 2) + "'aria-label='Next'>" + "<span aria-hidden='true'>&raquo;</span>"
                    + "</a></li>";
        }
        out.println(page);
    }
}