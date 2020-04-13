package org.usecases;

import lombok.Getter;
import lombok.Setter;

import org.mybatis.model.Computer;
import org.mybatis.model.ComputerProcessor;
import org.mybatis.model.Processor;
import org.mybatis.dao.ComputerMapper;
import org.mybatis.dao.ProcessorMapper;
import org.mybatis.dao.ComputerProcessorMapper;

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
    private ComputerProcessorMapper computerProcessorMapper;

    @Getter
    private List<Processor> availableProcessors;

    @Getter @Setter
    private List<Processor> computerProcessors;

    @Getter @Setter
    private Computer computer;

    @Getter @Setter
    private Integer computerId;

    @Getter @Setter
    private Integer processorId;

    private void loadProcessors(){
        availableProcessors = processorMapper.selectAll();
        this.computerProcessors = computerMapper.selectProcessorsForComputer(computerId);
        availableProcessors.removeAll(computerProcessors);
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.computerId = Integer.parseInt(requestParameters.get("computerId"));
        this.computer = computerMapper.selectByPrimaryKey(computerId);
        loadProcessors();
    }

    @Transactional
    public String addProcessor(Processor processor) {
        ComputerProcessor computerProcessor = new ComputerProcessor();
        computerProcessor.setProcessorId(processor.getId());
        computerProcessor.setComputerId(this.computerId);
        computerProcessorMapper.insert(computerProcessor);
        return "computerProcessors?faces-redirect=true&computerId=" + this.computer.getId();
    }
}
