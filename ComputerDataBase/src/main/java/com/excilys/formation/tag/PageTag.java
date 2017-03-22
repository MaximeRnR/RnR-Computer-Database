package com.excilys.formation.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class PageTag extends SimpleTagSupport {
    private int nbPage;
    private int index;

    /**
     * @param nbPage nePage
     */
    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
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
            page = page + "<li id='previous'><a href='#' aria-label='Previous'>" + " <span aria-hidden='true'"
                    + ">&laquo;</span>" + "</a></li>";
        }
        for (int i = 0; i < nbPage + 1; i++) {

            if (i > index - 3 && i < index + 3) {
                if (i == index) {
                    page = page + "<li class='pages active' position='" + i + "'><a position='" + i + "'>" + (i + 1)
                            + "</a></li>";
                } else {
                    page = page + "<li class='pages' position='" + i + "'><a position='" + i + "'>" + (i + 1)
                            + "</a></li>";
                }
            }
        }

        if (index != nbPage) {
            page = page + "<li id='next'><a href='#' aria-label='Next'>" + "<span aria-hidden='true'>&raquo;</span>"
                    + "</a></li>";
        }
        out.println(page);
    }
}