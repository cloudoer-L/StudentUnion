package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.PersonDao;
import cloudoer.su.entity.Person;
import org.springframework.stereotype.Repository;

@Repository("personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao<Person> {
}
