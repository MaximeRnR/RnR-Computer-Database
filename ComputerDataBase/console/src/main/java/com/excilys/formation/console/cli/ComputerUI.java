package com.excilys.formation.console.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.excilys.formation.console.dto.CompanyDTO;
import com.excilys.formation.console.dto.ComputerDTO;
import com.excilys.formation.console.util.ComputerDBException;
import com.excilys.formation.console.util.Page;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


//Called in App
public class ComputerUI {
    private ComputerDTO cpDto;
    private List<ComputerDTO> lcpDto;
    private CompanyDTO cyDto;

    /**
     * @throws ComputerDBException cdbexc
     */
    @Produces("application/json")
    public void addComputer() {

        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/api");
        WebTarget write = base.path("computer/create");
        Invocation.Builder builder = write.request(MediaType.APPLICATION_JSON_TYPE);
        System.out.println("Add a computer");
        System.out.print("Enter a name : ");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        if (name == null) {
            throw new ComputerDBException("Missing Name");
        }
        cyDto = new CompanyDTO(0);
        cpDto = new ComputerDTO.Builder()
                .name(name)
                .di(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .dd(null)
                .cydtoId(cyDto.getId())
                .build();
        builder.post(Entity.entity(cpDto, MediaType.APPLICATION_JSON));
    }

    /**
     * @throws ComputerDBException cdbex
     */
    public void removeComputer() throws ComputerDBException {

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
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/api");
        WebTarget delete = base.path("computer/delete/" + idTabs.get(0));
        Invocation.Builder builder = delete.request(MediaType.APPLICATION_JSON_TYPE);
        builder.delete();

    }

    /**
     * @throws ComputerDBException cdbex
     */
    public void updateComputer() throws ComputerDBException {

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
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/api");
        WebTarget find = base.path("computer/" + id);
        Invocation.Builder builder = find.request(MediaType.APPLICATION_JSON_TYPE);
        cpDto = builder.get(ComputerDTO.class);
        System.out.println("What do you want to update");
        System.out.println("\t 1 - Name");
        System.out.println("\t 2 - Discontinued");
        int choice = 1;
        try {
            choice = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Not a number");
            Cli.menu();
        }
        switch (choice) {
            case 1:
                System.out.print(cpDto.getName() + " to : ");
                String name = scan.next();
                if (name == null) {
                    throw new ComputerDBException("Missing Name");
                }
                cpDto.setName(name);
                
                break;
            case 2:
                System.out.print(cpDto.getDateDiscontinued() + " to (yyyy-MM-dd) : ");
                cpDto.setDateDiscontinued(scan.next());
                break;

        }
        client = ClientBuilder.newClient();
        base = client.target("http://localhost:8080/webapp/api");
        WebTarget update = base.path("computer/update");
        builder = update.request(MediaType.APPLICATION_JSON_TYPE);
        builder.put(Entity.entity(cpDto, MediaType.APPLICATION_JSON));
    }

    /**
     * @return cp ComputerDTO
     * @throws ComputerDBException cdbex
     */
    public ComputerDTO findComputer() throws ComputerDBException {

        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/api");
        System.out.println("Find a computer");
        System.out.print("Enter a id : ");
        Scanner scan = new Scanner(System.in);
        long id = 0;
        try {
            id = scan.nextLong();
        } catch (ComputerDBException | InputMismatchException e) {
            throw new ComputerDBException("Not a Number", e);
        }
        WebTarget find = base.path("computer/" + id);
        Invocation.Builder builder = find.request(MediaType.APPLICATION_JSON_TYPE);
        cpDto = builder.get(ComputerDTO.class);
        System.out.println();
        return cpDto;

    }

    

    /**
     * @return lcp List<ComputerDTO>
     * @throws ComputerDBException cdbex
     */
    public List<ComputerDTO> page() throws ComputerDBException {
        Page page = new Page();
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/api");
        WebTarget countPages = base.path("computers/pages/count");
        Invocation.Builder builder = countPages.request(MediaType.APPLICATION_JSON_TYPE);
        int nbPage = builder.get(Integer.class);
        System.out.println("There are " + nbPage + " pages .");
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
        client = ClientBuilder.newClient();
        base = client.target("http://localhost:8080/webapp/api");
        WebTarget findall = base.path("computers");
        WebTarget findally = findall.queryParam("page",index);
        builder = findally.request(MediaType.APPLICATION_JSON_TYPE);
        lcpDto = builder.get(new GenericType<List<ComputerDTO>>(){});
        System.out.println(lcpDto.size());
        return lcpDto;
    }

}