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
public class BoardServiceImpl implements BoardService {
	
	@Autowired //boardDAO 가져오기위해서
	private BoardDAO bDao;
	
	@Autowired //ReplyDAO 가져오기위해서
	private ReplyDAO rDao;

	@Transactional
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

	@Transactional
	@Override
	public BoardVO read(int bno) {
		bDao.increaseViewCnt(bno);
		return bDao.read(bno);
	}

	@Override
	public BoardVO updateui(int bno) {
		return bDao.updateui(bno);
	}

	@Transactional
	@Override
	public void update(BoardVO vo) {
		bDao.update(vo);
		
//		기존 업로드DB에 있던 파일은 삭제하고,
//		(attach 테이블의 특정 bno를 갖고 있는 레코드 다 삭제)
//		수정화면으로 받은 기존파일도 새로운 파일로서 재업로드		
		bDao.delByBno(vo.getBno());		
		
		String[] files = vo.getFiles();
		if (files != null) {
			//첨부파일이 있을때
			for(String fullName : files) {
				bDao.addAttach(fullName, vo.getBno());
			}
		}
		
	}

	@Transactional
	@Override
	public void delete(int bno) {
		bDao.delByBno(bno); //본문삭제 전에 업로드파일 삭제되도록
		rDao.deleteByBno(bno); //본문삭제 전에 댓글 삭제되도록
		bDao.delete(bno);
		
	}

	@Override
	public PageTo<BoardVO> list(PageTo<BoardVO> to) {
		return bDao.list(to);
	}

	@Override
	public List<BoardVO> searchlist(String searchType, String keyword) {
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

	@Override
	public void delAttachFileName(String fileName) {
		bDao.delAttachFileName(fileName);
		
	}

	
	

}
