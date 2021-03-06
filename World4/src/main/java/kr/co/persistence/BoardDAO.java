package kr.co.persistence;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardDAO {
	
	void insert(BoardVO vo);

	List<BoardVO> list();

	BoardVO read(int bno);

	void increaseViewCnt(int bno);

	BoardVO updateui(int bno);

	void update(BoardVO vo);

	void delete(int bno);
}
