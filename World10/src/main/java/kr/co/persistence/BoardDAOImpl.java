package kr.co.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTo;
import kr.co.domain.ReplyVO;

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
		/*
		//댓글값 추가
		Integer repCnt = session.selectOne(NS+".getRepCnt");
		BoardVO vo = new BoardVO();
		vo.setRepCnt(repCnt);*/
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

	@Override
	public PageTo<BoardVO> list(PageTo<BoardVO> to) {		
//		RowBounds rowBounds = new RowBounds(offset, limit)
//											└어디서부터 └몇개 가져올지
		RowBounds rowBounds = new RowBounds(to.getStartNum()-1, to.getPerPage());
//		프로그래밍시 일반적인 인덱스는 0부터 시작
//		오라클, stack 만 인덱스가 1부터 시작
		
		
//		session.selectList(statement, parameter, rowBounds);
		List<BoardVO> list = session.selectList(NS+".list", null, rowBounds);
		
		to.setList(list);
		
		Integer amount = session.selectOne(NS+".getAmount");
		if (amount != null) {
			to.setAmount(amount);
		} else {
			to.setAmount(0);
		}
		
		return to;
	}

	@Override
	public List<BoardVO> searchlist(String searchType, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectList(NS+".searchlist", map);
//		return session.selectList(NS+".searchlist", map, RowBounds); //페이징처리시
	}

	@Override
	public BoardVO searchread(int bno, String searchType, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return session.selectOne(NS+".searchread", map);
	}

	@Override
	public void updateRepCnt(int bno, int amount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("amount", amount);
		session.update(NS+".updateRepCnt", map);
		
	}

		
	

	
}
