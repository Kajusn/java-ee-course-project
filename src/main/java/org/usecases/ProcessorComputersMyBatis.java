package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.model.Processor;
import org.mybatis.dao.ProcessorMapper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

public class ProcessorComputersMyBatis {
    @Inject
    private ProcessorMapper processorMapper;

    @Getter
    @Setter
    private Processor processor;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer processorId = Integer.parseInt(requestParameters.get("processorId"));
        this.processor = processorMapper.selectByPrimaryKey(processorId);
    }
}
