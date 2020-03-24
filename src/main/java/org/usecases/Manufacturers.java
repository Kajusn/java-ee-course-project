package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Manufacturer;
import org.persistence.ManufacturersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Manufacturers {

    @Inject
    private ManufacturersDAO manufacturersDAO;

    @Getter @Setter
    private Manufacturer manufacturerToCreate = new Manufacturer();

    @Getter
    private List<Manufacturer> allManufacturers;

    @PostConstruct
    public void init(){
        loadAllManufacturers();
    }

    @Transactional
    public String createManufacturer(){
        this.manufacturersDAO.persist(manufacturerToCreate);
        return "success";
    }

    private void loadAllManufacturers(){
        this.allManufacturers = manufacturersDAO.loadAll();
    }
}
