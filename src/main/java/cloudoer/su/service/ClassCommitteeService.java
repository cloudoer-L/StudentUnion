package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.ClassCommittee;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public interface ClassCommitteeService extends BaseService {
    List<ClassCommittee> getAll();

    List<ClassCommittee> getByPage(int pageNo, int pageSize);

    ClassCommittee getById(String id);

    ClassCommittee getByNumber(String number);

    String add(String classesId, String studentId, String positionId);

    void update (String classesId, String studentId, String positionId, String classCommitteeId);

    void delete (String id);

    String importFile(File file) throws ServiceException;

    void exportFile(OutputStream os)throws Exception;
}
