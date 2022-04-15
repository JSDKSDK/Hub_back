package com.gs.service;

import com.gs.dto.api.ApiSuccessResponse;
import com.gs.exception.MasterException;

public interface IService<TInput> {
    ApiSuccessResponse create(TInput dto) throws MasterException;

    ApiSuccessResponse update(TInput dto) throws MasterException;

    ApiSuccessResponse changeStatus(Object dto);

    ApiSuccessResponse listAll() throws MasterException;

    ApiSuccessResponse getById(int id) throws MasterException;
}
