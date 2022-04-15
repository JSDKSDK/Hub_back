package com.gs.repository.server.note;

import com.gs.model.database.DbMessageModel;
import com.gs.model.server.note.ServerNoteDetailModel;
import com.gs.model.server.note.ServerNoteModel;
import com.gs.repository.IRepository;

public interface IServerNoteRepository extends IRepository<ServerNoteModel, ServerNoteDetailModel> {

    DbMessageModel<ServerNoteDetailModel> listByServerId(int serverId);
}
