package com.vicente.springboothateoas.controllers.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class ApiErrorList extends ApiError {

    private static final long serialVersionUID = 1L;

    private List<String> erros;

    public ApiErrorList(int code, String msg, Date date, List<String> erros) {
        super(code, msg, date);
        this.erros = erros;
    }
}