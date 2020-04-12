package org.mybatis.dao;

import org.mybatis.cdi.Mapper;
import org.mybatis.model.Computer;
import org.mybatis.model.ComputerProcessor;
import org.mybatis.model.Processor;

import java.util.List;

@Mapper
public interface ComputerProcessorMapper {

    int deleteProcessor(Integer computerId, Integer processorId);
    int addProcessor(Integer computerId, Integer processorId);
    List<Processor> getProcessors(Integer computerId);
    List<Computer> getComputers(Integer processorId);
    int insert(ComputerProcessor record);
}