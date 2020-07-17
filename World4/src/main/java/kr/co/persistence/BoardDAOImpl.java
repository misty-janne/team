package kr.co.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;
	
	private final String NS = "b.o.a";

	@Override
	public void insert(BoardVO vo) {
		
		//bno 값 불러오기
		Integer bno = session.selectOne(NS+".getBno");
		if (bno != null) {
//				└null이 아닌경우가 많이 때문에 속도개선을 위해서
			bno += 1;
		} else {
			bno = 1;
		}		
		vo.setBno(bno);
		
		session.insert(NS+".insert", vo);		
	}

	@Override
	public List<BoardVO> list() {
		return session.selectList(NS+".list");
	}

	@Override
	public BoardVO read(int bno) {
		return session.selectOne(NS+".read", bno);
	}

	@Override
	public void increaseViewCnt(int bno) {
		session.update(NS+".increaseViewCnt", bno);
	}

	@Override
	public BoardVO updateui(int bno) {
		return session.selectOne(NS+".updateui", bno);
	}

	@Override
	public void update(BoardVO vo) {
		session.update(NS+".update", vo);
		
	}

	@Override
	public void delete(int bno) {
		session.delete(NS+".delete", bno);
		
	}

	
}
