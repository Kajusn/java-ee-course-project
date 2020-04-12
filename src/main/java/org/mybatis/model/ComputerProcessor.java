package org.mybatis.model;

public class ComputerProcessor {

    private Integer computerId;
    private Integer processorId;

    public void setComputerId(Integer id) {
        this.computerId = id;
    }
    public void setProcessorId(Integer id) {
        this.processorId = id;
    }
    public Integer getComputerId() {
        return this.computerId;
    }
    public Integer getProcessorId() {
        return this.processorId;
    }
}