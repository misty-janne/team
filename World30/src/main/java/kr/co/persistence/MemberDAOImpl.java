package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;

@Repository //객체를 만들겠다는 표시
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession session;
	
	private final String NS="m.e.m";
	
	

	@Override
	public void insert(MemberDTO dto) {
//		session.insert(statement, parameter);
//						└mapper의 id
		session.insert(NS+".insert", dto);
		
	}



	@Override
	public List<MemberDTO> list() {
//		return session.selectList(statement, parameter, rowBounds); //페이징처리 경우
		return session.selectList(NS+".list");
	}



	@Override
	public MemberDTO read(String id) {
		return session.selectOne(NS+".read", id);
	}



	@Override
	public MemberDTO updateui(String id) {
		return session.selectOne(NS+".updateui", id);
	}



	@Override
	public void update(MemberDTO dto) {
		session.update(NS+".update", dto);
		
	}



	@Override
	public void delete(String id) {
		session.delete(NS+".delete", id);
	}



	@Override
	public MemberDTO loginpost(LoginDTO login) {		
		return session.selectOne(NS+".loginpost", login);
	}

}
