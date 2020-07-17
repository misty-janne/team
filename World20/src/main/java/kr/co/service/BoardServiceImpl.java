package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTo;
import kr.co.persistence.BoardDAO;
import kr.co.persistence.ReplyDAO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired //boardDAO 가져오기위해서
	private BoardDAO bDao;
	
	@Autowired //ReplyDAO 가져오기위해서
	private ReplyDAO rDao;

	@Override
	public void insert(BoardVO vo) {
		bDao.insert(vo);
		String[] files = vo.getFiles();
		if (files != null) {
			//첨부파일이 있을때
			for(String fullName : files) {
				bDao.addAttach(fullName, vo.getBno());
			}
		}
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
		rDao.deleteByBno(bno); //본문삭제 전에 댓글삭제되도록
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

	@Override
	public List<String> getAttach(Integer bno) {

		return bDao.getAttach(bno);
	}

	
	

}
