package kr.co.ca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.service.TranTestService;

@Controller
@RequestMapping("board")
public class TranTestController {

	@Autowired
	private TranTestService ttService;
	
	//트랜잭션 적용X
	@RequestMapping(value = "insertNupdate1", method = RequestMethod.GET)
	public void insertNupdate1() {
//		BoardVO vo = new BoardVO(bno, title, content, writer, viewCnt, regDate, updateDate, repCnt)
		BoardVO vo = new BoardVO(-1, "hello", "world", "kim", -1, null, null, 0);
		ttService.insertNupdate1(vo);
	}
}
