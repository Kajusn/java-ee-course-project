package org.mybatis.dao;

import org.mybatis.cdi.Mapper;
import org.mybatis.model.Computer;
import org.mybatis.model.Manufacturer;
import org.mybatis.model.Processor;

import java.util.List;

@Mapper
public interface ComputerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COMPUTER
     *
     * @mbg.generated Fri Apr 10 15:44:35 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COMPUTER
     *
     * @mbg.generated Fri Apr 10 15:44:35 EEST 2020
     */
    int insert(Computer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COMPUTER
     *
     * @mbg.generated Fri Apr 10 15:44:35 EEST 2020
     */
    Computer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COMPUTER
     *
     * @mbg.generated Fri Apr 10 15:44:35 EEST 2020
     */
    List<Computer> selectAll();

    List<Computer> selectComputerByManufacturerId(Integer id);

    void setProcessors(List<Processor> processorList);

    Manufacturer getManufacturer();

    void setManufacturer(Manufacturer manufacturer);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COMPUTER
     *
     * @mbg.generated Fri Apr 10 15:44:35 EEST 2020
     */
    int updateByPrimaryKey(Computer record);
}