package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTo;

public interface BoardDAO {
	
	void insert(BoardVO vo);
	
	//insert시 attach 테이블도 함께 변경시키기
	void addAttach(String fullName, int bno);

	List<BoardVO> list();

	BoardVO read(int bno);

	void increaseViewCnt(int bno);

	BoardVO updateui(int bno);

	void update(BoardVO vo);

	void delete(int bno);

	PageTo<BoardVO> list(PageTo<BoardVO> to);

	List<BoardVO> searchlist(String searchType, String keyword);

	BoardVO searchread(int bno, String searchType, String keyword);

	void updateRepCnt(@Param("bno") int bno, @Param("amount") int amount);

	List<String> getAttach(Integer bno);
}
