package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.Position;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public interface PositionService extends BaseService {
    List<Position> getAll();

    List<Position> getByPage(int pageNo, int pageSize);

    Position getById(String id);

    Position getByNumber(String number);

    String add(Position position);

    void update (Position position);

    void delete (String id);

    String importFile(File file) throws ServiceException;

    void exportFile(OutputStream os)throws Exception;
}
