package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		// TODO Auto-generated method stub
//		return session.selectList(statement, parameter, rowBounds); //페이징처리 경우
		return session.selectList(NS+".list");
	}

}
