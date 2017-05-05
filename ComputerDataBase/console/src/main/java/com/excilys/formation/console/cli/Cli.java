package com.excilys.formation.console.cli;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.console.dto.CompanyDTO;
import com.excilys.formation.console.dto.ComputerDTO;


public class Cli {

    /**
     */
    public Cli() {
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
        System.out.println("\t 2 - Show a Page of 10 computers");
        System.out.println("\t 3 - Show details for one computer");
        System.out.println("\t 4 - Create a Computer");
        System.out.println("\t 5 - Update a Computer");
        System.out.println("\t 6 - Delete a Computer");
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        try {
            choice = scan.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Pick a menu number");
            Cli.menu();
        }

        if (choice > 7 || choice < 1) {
            System.out.println("Pick a menu number");
            Cli.menu();
        }

        switch (choice) {
            case 1:
                List<CompanyDTO> lcy = cyui.findAllCompany();
                for (int i = 0; i < lcy.size(); i++) {

                    System.out.println(lcy.get(i).toString());

                }
                break;
            case 2:
                List<ComputerDTO> lcp = cpui.page();
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
                        Cli.menu();
                    }

                    if (pageTest > 2 || pageTest < 0) {
                        System.out.println("Pick a menu number");
                        Cli.menu();
                    }
                    switch (pageTest) {
                        case 1:
                            lcp = cpui.page();
                            for (int i = 0; i < lcp.size(); i++) {

                                System.out.println(lcp.get(i).toString());

                            }
                            break;
                        case 2:
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
                System.out.println(cp.getId() + " " + cp.getName() + " " + cp.getDateIntroduced() + " "
                        + cp.getDateDiscontinued() + " " + cp.getCydtoName());

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