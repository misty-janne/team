package kr.co.persistence;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTo;

public interface BoardDAO {
	
	void insert(BoardVO vo);

	List<BoardVO> list();

	BoardVO read(int bno);

	void increaseViewCnt(int bno);

	BoardVO updateui(int bno);

	void update(BoardVO vo);

	void delete(int bno);

	PageTo<BoardVO> list(PageTo<BoardVO> to);

	List<BoardVO> searchlist(String searchType, String keyword);

	BoardVO searchread(int bno, String searchType, String keyword);

}
