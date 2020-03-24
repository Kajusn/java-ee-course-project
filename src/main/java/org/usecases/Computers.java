/*package org.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.entities.Computer;
import org.persistence.ComputersDAO;

@Model
public class Computers implements Serializable {

    @Inject
    private ComputersDAO computersDAO;

    private Computer computerToCreate = new Computer();

    private List<Computer> allComputers;
    @PostConstruct
    public void init(){
        loadComputers();
    }

    public void loadComputers() {
        this.allComputers = computersDAO.loadAll();
    }

    public List<Computer> getAllComputers(){
        return allComputers;
    }

    @Transactional
    public String createComputer(){
        this.computersDAO.persist(computerToCreate);
        return "success";
    }

    public Computer getComputerToCreate() {
        return computerToCreate;
    }

    public void setComputerToCreate(Computer computerToCreate) {
        this.computerToCreate = computerToCreate;
    }
}
*/