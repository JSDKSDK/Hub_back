package com.gs.controller;

import com.gs.dto.api.ApiSuccessResponse;
import org.springframework.http.ResponseEntity;

public interface IController<TInput> {

    ResponseEntity<ApiSuccessResponse> create(TInput body);
    ResponseEntity<ApiSuccessResponse> update(TInput body);
    ResponseEntity<ApiSuccessResponse> changeStatus(Object body);
    ResponseEntity<ApiSuccessResponse> listAll();
    ResponseEntity<ApiSuccessResponse> getById(int id);
}
