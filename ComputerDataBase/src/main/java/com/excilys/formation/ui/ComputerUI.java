package com.excilys.formation.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.util.ComputerDBException;


//Called in App
public class ComputerUI {
    private ComputerService cpS;
    private ComputerDTO cp;
    private CompanyDTO cy;
    private Page page;

    /**
     * @throws ComputerDBException cdbexc
     */
    public void addComputer() throws ComputerDBException {

        cpS = ComputerService.INSTANCE;
        System.out.println("Add a computer");
        System.out.print("Enter a name : ");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        if (name == null) {
            throw new ComputerDBException("Missing Name");
        }
        cy = new CompanyDTO(44);
        cp = new ComputerDTO.Builder().name(name).di(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).dd(null).cydto(cy).build();
        cpS.create(cp);
    }

    /**
     * @throws ComputerDBException cdbex
     */
    public void removeComputer() throws ComputerDBException {

        cpS = ComputerService.INSTANCE;
        System.out.println("Remove a computer");
        System.out.print("Enter a id : ");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        String ids = "0";
        List<Long> idTabs = new ArrayList<>();
        try {
            ids = scan.next();
            idTabs.add((long) Integer.parseInt(ids));
        } catch (ComputerDBException | InputMismatchException e) {
            throw new ComputerDBException("Not a Number", e);
        }
        cpS.delete(idTabs);

    }

    /**
     * @throws ComputerDBException cdbex
     */
    public void updateComputer() throws ComputerDBException {

        cpS = ComputerService.INSTANCE;
        System.out.println("Update a computer");
        System.out.print("Enter a id : ");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        long id = 0;
        try {
            id = scan.nextLong();
        } catch (ComputerDBException | InputMismatchException e) {
            throw new ComputerDBException("Not a Number", e);
        }
        cp = cpS.findById(id);
        System.out.println("What do you want to update");
        System.out.println("\t 1 - Name");
        System.out.println("\t 2 - Discontinued");
        int choice = 1;
        try {
            choice = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Not a number");
            App.menu();
        }
        switch (choice) {
            case 1:
                System.out.print(cp.getName() + " to : ");
                String name = scan.next();
                if (name == null) {
                    throw new ComputerDBException("Missing Name");
                }
                cp.setName(name);
                cpS.update(cp);
                break;
            case 2:
                System.out.print(cp.getdDiscontinued() + " to (yyyy-MM-dd) : ");
                cp.setdDiscontinued(scan.next());
                cpS.update(cp);
                break;

        }
    }

    /**
     * @return cp ComputerDTO
     * @throws ComputerDBException cdbex
     */
    public ComputerDTO findComputer() throws ComputerDBException {

        cpS = ComputerService.INSTANCE;
        System.out.println("Find a computer");
        System.out.print("Enter a id : ");
        Scanner scan = new Scanner(System.in);
        long id = 0;
        try {
            id = scan.nextLong();
        } catch (ComputerDBException | InputMismatchException e) {
            throw new ComputerDBException("Not a Number", e);
        }
        System.out.println();
        cp = cpS.findById(id);
        return cp;

    }

    /**
     * @return lcp List<ComputerDTO>
     * @throws ComputerDBException cdbex
     */
    public List<ComputerDTO> firstPage() throws ComputerDBException {

        cpS = ComputerService.INSTANCE;
        System.out.println("There are " + cpS.getNumberOfPageOfAllComputers(page) + ".");
        System.out.print("Enter a page : ");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        int index = 0;
        try {
            index = scan.nextInt();
        } catch (InputMismatchException e) {
            throw new ComputerDBException("Not a Number", e);
        }
        page.setIndex(index);

        return cpS.getPageOfComputers(page);
    }

    /**
     * @return lcp List<ComputerDTO>
     * @throws ComputerDBException cdbex
     */
    public List<ComputerDTO> page() throws ComputerDBException {

        return cpS.getPageOfComputers(page);
    }

}
