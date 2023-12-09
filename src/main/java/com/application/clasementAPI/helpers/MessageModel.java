package com.application.clasementAPI.helpers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
public class MessageModel {
    private String message;
    private boolean status;
    private Object pagination;
    private Object data;

    public MessageModel(){};

    public MessageModel setResponseMessage(
            Object pagination,
            Object data
    ) {
        if(data instanceof Collection<?>) {
            if(((Collection<?>) data).isEmpty()) {
                this.message = "Data tidak ditemukan";
                this.status = false;
                this.pagination = null;
                this.data = new ArrayList<>();
            }else {
                this.message = "Berhasil mengambil data";
                this.status = true;
                this.pagination = pagination;
                this.data = data;
            }
        }else {
            this.message = "Berhasil mengambil data";
            this.status = true;
            this.pagination = pagination;
            this.data = data;
        }

        return this;
    }
}
