package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.Classes;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public interface ClassesService extends BaseService {
    List<Classes> getAll();

    List<Classes> getByPage(int pageNo, int pageSize);

    Classes getById(String id);

    Classes getByNumber(String number);

    String add(Classes classes, String teacherId);

    void update (Classes classes, String teacherId);

    void delete (String id);

    String importFile(File file) throws ServiceException;

    void exportFile(OutputStream os)throws Exception;
}
