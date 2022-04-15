package com.gs.repository;

import com.gs.model.database.DbMessageModel;

public interface IRepository<TInput, TOutput> {
    DbMessageModel create(TInput model);

    DbMessageModel update(TInput model);

    DbMessageModel changeStatus(Object model);

    DbMessageModel<TOutput> listAll();

    DbMessageModel<TOutput> getById(int id);

}
