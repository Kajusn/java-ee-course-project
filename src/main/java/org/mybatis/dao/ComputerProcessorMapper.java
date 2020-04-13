package org.mybatis.dao;

import org.mybatis.cdi.Mapper;
import org.mybatis.model.ComputerProcessor;

@Mapper
public interface ComputerProcessorMapper {

    int deleteProcessor(Integer computerId, Integer processorId);
    int addProcessor(Integer computerId, Integer processorId);
    int insert(ComputerProcessor record);
}