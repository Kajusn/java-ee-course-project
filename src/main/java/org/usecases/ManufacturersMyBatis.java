package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.model.Manufacturer;
import org.mybatis.dao.ManufacturerMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ManufacturersMyBatis {
    @Inject
    private ManufacturerMapper manufacturerMapper;

    @Getter
    @Setter
    private Manufacturer manufacturerToCreate = new Manufacturer();

    @Getter
    private List<Manufacturer> allManufacturers;

    @PostConstruct
    public void init(){
        loadAllManufacturers();
    }

    @Transactional
    public String createManufacturer(){
        manufacturerMapper.insert(manufacturerToCreate);
        return "manufacturers?faces-redirect=true";
    }

    private void loadAllManufacturers(){
        this.allManufacturers = manufacturerMapper.selectAll();
    }
}
