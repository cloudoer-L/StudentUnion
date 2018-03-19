package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.MemberDao;
import cloudoer.su.entity.Member;
import org.springframework.stereotype.Repository;

@Repository("memberDao")
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao<Member> {
}
