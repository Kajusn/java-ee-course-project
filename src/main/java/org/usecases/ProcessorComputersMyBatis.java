package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.dao.ComputerProcessorMapper;
import org.mybatis.model.Computer;
import org.mybatis.model.Processor;
import org.mybatis.dao.ProcessorMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model
public class ProcessorComputersMyBatis {
    @Inject
    private ProcessorMapper processorMapper;

    @Getter @Setter
    private Integer processorId;

    @Getter @Setter
    private Processor processor;

    @Getter @Setter
    private List<Computer> processorComputers;

    public void loadComputers() {
        this.processorComputers = processorMapper.selectComputersForProcessor(processorId);
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.processorId = Integer.parseInt(requestParameters.get("processorId"));
        this.processor = processorMapper.selectByPrimaryKey(processorId);
        loadComputers();
    }
}
