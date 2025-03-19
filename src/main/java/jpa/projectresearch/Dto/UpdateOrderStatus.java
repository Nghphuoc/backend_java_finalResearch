package jpa.projectresearch.Dto;

import jpa.projectresearch.Variable.Variable;

public class UpdateOrderStatus {

    private Variable.setStatus status;

    public Variable.setStatus getStatus() {
        return status;
    }

    public void setStatus(Variable.setStatus status) {
        this.status = status;
    }

    public UpdateOrderStatus() {

    }

    public UpdateOrderStatus(Variable.setStatus status) {
        this.status = status;
    }

}
