package com.excilys.formation.validator;

public class ParameterValidator {


    /**
     * @param name name
     * @return boolean true/false
     */
    public static boolean checkName(String name) {

        return (name != null && !name.isEmpty())
                && name.matches("^[a-zA-Z0-9 -._]+$");

    }

    /**
     * @param dateIntroduced dateIntroduced
     * @return boolean true/false
     */
    public static boolean checkDateIntroduced(String dateIntroduced) {

        return (dateIntroduced != null && dateIntroduced.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$"))
                || dateIntroduced == "";

    }

    /**
     * @param dateDiscontinued dateDiscontinued
     * @return boolean true/false
     */
    public static boolean checkDateDiscontinued(String dateDiscontinued) {

        return (dateDiscontinued != null && dateDiscontinued.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$"))
                || dateDiscontinued == "";

    }


    /**
     * @param pageNumber pageNumber
     * @param nbPages nbPages
     * @return boolean true/false
     * @throws NumberFormatException numberexcp
     */
    public static boolean checkPageNumber(String pageNumber, int nbPages) throws NumberFormatException {

        return pageNumber != null &&
                pageNumber != "" &&
                Integer.parseInt(pageNumber) > 1 &&
                Integer.parseInt(pageNumber) < (nbPages + 2);

    }

    /**
     * @param maxObj maxObj
     * @return boolean true/false
     * @throws NumberFormatException numberexcp
     */
    public static boolean checkMaxObj(String maxObj) throws NumberFormatException {

        return maxObj != null &&
                maxObj != "" &&
                (Integer.parseInt(maxObj) == 10 ||
                 Integer.parseInt(maxObj) == 50 ||
                 Integer.parseInt(maxObj) == 100);
    }

    /**
     * @param search search
     * @return boolean true/false
     */
    public static boolean checkSearch(String search) {

        return search != null && search != "";
    }

    /**
     * @param by by
     * @return boolean true/false
     */
    public static boolean checkBy(String by) {

        return by != null &&
                by != "" &&
                (by.equals("cp") ||
                 by.equals("cy"));
    }

}
