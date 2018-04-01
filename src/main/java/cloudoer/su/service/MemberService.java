package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.Member;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public interface MemberService extends BaseService {
    List<Member> getAll();

    List<Member> getByPage(int pageNo, int pageSize);

    Member getById(String id);

    Member getByNumber(String number);

    String add(Member member,String personNumber, String departmentNumber);

    void update (Member member,String personNumber, String departmentNumber);

    void delete (String id);

    String importFile(File file) throws ServiceException;

    void exportFile(OutputStream os)throws Exception;
}
