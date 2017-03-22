package com.excilys.formation.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;

public class App {

    /**
     */
    public App() {
    }

    /**
     * @param args args
     */
    public static void main(String[] args) {

        menu();

    }

    /**
     */
    public static void menu() {
        CompanyUI cyui = new CompanyUI();
        ComputerUI cpui = new ComputerUI();
        System.out.println("CDB - What do you want to do ?");
        System.out.println("\t 1 - Show all Companies");
        System.out.println("\t 2 - Show all Computers");
        System.out.println("\t 3 - Show details for one computer");
        System.out.println("\t 4 - Create a Computer");
        System.out.println("\t 5 - Update a Computer");
        System.out.println("\t 6 - Delete a Computer");
        System.out.println("\t 7 - Show a Page of 10 computers");
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        try {
            choice = scan.nextInt();

        } catch (Exception e) {
            System.out.println("Pick a menu number");
            App.menu();
        }

        if (choice > 7 || choice < 1) {
            System.out.println("Pick a menu number");
            App.menu();
        }

        switch (choice) {
            case 1:
                List<CompanyDTO> lcy = cyui.findAllCompany();
                for (int i = 0; i < lcy.size(); i++) {

                    System.out.println(lcy.get(i).toString());

                }
                break;
            case 2:
                List<ComputerDTO> lcp = cpui.firstPage();
                for (int i = 0; i < lcp.size(); i++) {

                    System.out.println(lcp.get(i).toString());

                }
                int quit = 0;
                while (quit == 0) {
                    System.out.println("Page menu");
                    System.out.println("\t 1 - Previous");
                    System.out.println("\t 2 - Next");
                    System.out.println("\t 0 - Quit");
                    int pageTest = 0;
                    try {
                        pageTest = scan.nextInt();

                    } catch (InputMismatchException e) {
                        System.out.println("Pick a menu number");
                        App.menu();
                    }

                    if (pageTest > 2 || pageTest < 0) {
                        System.out.println("Pick a menu number");
                        App.menu();
                    }
                    switch (pageTest) {
                        case 1:
                            Page.PAGE.previous();
                            lcp = cpui.page();
                            for (int i = 0; i < lcp.size(); i++) {

                                System.out.println(lcp.get(i).toString());

                            }
                            break;
                        case 2:
                            Page.PAGE.next();
                            lcp = cpui.page();
                            for (int i = 0; i < lcp.size(); i++) {

                                System.out.println(lcp.get(i).toString());

                            }
                            break;
                        case 0:
                            quit = 1;
                            break;
                    }
                }
                break;
            case 3:
                ComputerDTO cp = cpui.findComputer();
                System.out.println(cp.getId() + " " + cp.getName() + " " + cp.getdIntroduced() + " "
                        + cp.getdDiscontinued() + " " + cp.getCydto().getName());

                break;
            case 4:
                cpui.addComputer();

                break;
            case 5:
                cpui.updateComputer();

                break;
            case 6:
                cpui.removeComputer();
                break;
            case 7:
                /*
                 * List<Computer> lcpP = cpui.PageComputer(); for(int i=0;
                 * i<lcpP.size();i++){
                 * System.out.println(lcpP.get(i).toString()); }
                 */
                break;
        }
        pressAnyKeyToContinue();

    }

    /**
     */
    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
            menu();
        } catch (Exception e) {
        }
    }

}
