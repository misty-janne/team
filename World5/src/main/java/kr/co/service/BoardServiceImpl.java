package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTo;
import kr.co.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired //boardDAO 가져오기위해서
	private BoardDAO bDao;

	@Override
	public void insert(BoardVO vo) {
		bDao.insert(vo);
		
	}

	@Override
	public List<BoardVO> list() {
		return bDao.list();
	}

	@Override
	public BoardVO read(int bno) {
		bDao.increaseViewCnt(bno);
		return bDao.read(bno);
	}

	@Override
	public BoardVO updateui(int bno) {
		return bDao.updateui(bno);
	}

	@Override
	public void update(BoardVO vo) {
		bDao.update(vo);
		
	}

	@Override
	public void delete(int bno) {
		bDao.delete(bno);
		
	}

	@Override
	public PageTo<BoardVO> list(PageTo<BoardVO> to) {

		return bDao.list(to);
	}

	@Override
	public List<BoardVO> searchlist(String searchType, String keyword) {
		// TODO Auto-generated method stub
		return bDao.searchlist(searchType, keyword);
	}

	@Override
	public BoardVO searchread(int bno, String searchType, String keyword) {
		return bDao.searchread(bno, searchType, keyword);
	}

	
	

}
