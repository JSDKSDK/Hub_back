package com.gs.repository.direction;

import com.gs.model.database.DbMessageModel;
import com.gs.model.direction.DirectionDetailModel;
import com.gs.model.direction.DirectionModel;
import com.gs.repository.IRepository;

public interface IDirectionRepository extends IRepository<DirectionModel, DirectionDetailModel> {

    DbMessageModel<DirectionDetailModel> listByBusinessId(int businessId);
}
