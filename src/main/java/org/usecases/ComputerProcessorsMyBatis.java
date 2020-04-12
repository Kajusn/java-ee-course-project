package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.model.Computer;
import org.mybatis.model.Manufacturer;
import org.mybatis.model.Processor;
import org.mybatis.dao.ComputerMapper;
import org.mybatis.dao.ManufacturerMapper;
import org.mybatis.dao.ProcessorMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class ComputerProcessorsMyBatis {
    @Inject
    private ComputerMapper computerMapper;

    @Inject
    private ProcessorMapper processorMapper;

    @Inject
    private ManufacturerMapper manufacturerMapper;

    @Getter
    private List<Processor> availableProcessors;

    @Getter @Setter
    private Computer computer;

    private void loadProcessors(){
        availableProcessors = processorMapper.selectAll();
        //availableProcessors.removeAll(computer.getProcessors()); // Reikia taisyti
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer computerId = Integer.parseInt(requestParameters.get("computerId"));
        this.computer = computerMapper.selectByPrimaryKey(computerId);
        loadProcessors();
    }

    @Transactional
    public String addProcessor(Processor processor) {
        Manufacturer temp = manufacturerMapper.selectByPrimaryKey(this.computer.getManufacturerId());
        //processor.getComputers().add(this.computer);
        //computerMapper.update(this.computer);
        //processorMapper.update(processor);
        return "computerProcessors?faces-redirect=true&computerId=" + this.computer.getId();
    }
}
