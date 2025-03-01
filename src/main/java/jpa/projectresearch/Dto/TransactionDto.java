package jpa.projectresearch.Dto;

import java.io.Serializable;

public class TransactionDto implements Serializable {
    private String status;
    private String message;
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TransactionDto(String status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public TransactionDto() {}
}
