package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.dao.ProcessorMapper;
import org.mybatis.model.Processor;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ProcessorsMyBatis {
    @Inject
    private ProcessorMapper processorMapper;

    @Getter
    @Setter
    private Processor processorToCreate = new Processor();

    @Getter
    private List<Processor> allProcessors;

    @PostConstruct
    public void init() {
        loadAllProcessors();
    }

    @Transactional
    public String createProcessor(){
        processorMapper.insert(processorToCreate);
        return "mybatis/processors?faces-redirect=true";
    }

    private void loadAllProcessors(){
        this.allProcessors = processorMapper.selectAll();
    }
}
