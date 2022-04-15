package com.gs.repository.area;

import com.gs.model.area.AreaDetailModel;
import com.gs.model.area.AreaModel;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.IRepository;

public interface IAreaRepository extends IRepository<AreaModel, AreaDetailModel> {

    DbMessageModel<AreaDetailModel> listAllByDirectionId(int directionId);
}
