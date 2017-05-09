package com.excilys.formation.console.cli;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.excilys.formation.console.dto.CompanyDTO;
import com.excilys.formation.console.util.ComputerDBException;

//CAlled by App
public class CompanyUI {
    private CompanyDTO cyDto;
    private List<CompanyDTO> lcyDto;
    
    
    /**
     * @return cp ComputerDTO
     * @throws ComputerDBException cdbex
     */
    public CompanyDTO findCompany() throws ComputerDBException {

        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/api");
        System.out.println("Find a company");
        System.out.print("Enter a id : ");
        Scanner scan = new Scanner(System.in);
        long id = 0;
        try {
            id = scan.nextLong();
        } catch (ComputerDBException | InputMismatchException e) {
            throw new ComputerDBException("Not a Number", e);
        }
        WebTarget find = base.path("company/" + id);
        Invocation.Builder builder = find.request(MediaType.APPLICATION_JSON_TYPE);
        cyDto = builder.get(CompanyDTO.class);
        System.out.println();
        return cyDto;

    }

    /**
     * @return lcy List<Company>
     */
    public List<CompanyDTO> findAllCompany() {
        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/webapp/api");
        WebTarget findall = base.path("companies");
        Invocation.Builder builder = findall.request(MediaType.APPLICATION_JSON_TYPE);
        lcyDto = builder.get(new GenericType<List<CompanyDTO>>(){});
        System.out.println();
        return lcyDto;

    }
}