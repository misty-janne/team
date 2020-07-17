package com.naver.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTo;
import kr.co.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Inject
	private BoardService bService;

	
	@RequestMapping(value = "/searchread/{bno}", method = RequestMethod.GET)
	public String searchread(@PathVariable("bno") int bno, Model model, String searchType, String keyword) {
		BoardVO vo = bService.searchread(bno, searchType, keyword);
		model.addAttribute("vo", vo);
		model.addAttribute("bno", bno);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		return "board/searchread";
	}
	
	@RequestMapping(value = "/searchlist")
	public String searchlist(Model model, String searchType, String keyword) {
		List<BoardVO> list = bService.searchlist(searchType, keyword);
		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);

		return "board/searchlist";
	}

	@RequestMapping(value = "/delete/{bno}")
	public String delete(@PathVariable("bno") int bno) {
		bService.delete(bno);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO vo) {
		bService.update(vo);
		return "redirect:/board/read/"+vo.getBno();
	}
	
	@RequestMapping(value = "/update/{bno}", method = RequestMethod.GET)
	public String updateui(@PathVariable("bno") int bno, Model model) {
		BoardVO vo = bService.updateui(bno);
		model.addAttribute("vo", vo);
		return "board/update";
	}	

	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("bno") int bno, Model model) {
		BoardVO vo = bService.read(bno);
		model.addAttribute("vo", vo);
		return "board/read";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model, String curPage) {

		int page = -1;
		if (curPage != null) {
//			└페이지방문시 null인 경우가 많으므로 (속도개선)
			page = Integer.parseInt(curPage);
		} else {
			page = 1;
		}
		
		PageTo<BoardVO> to = new PageTo<BoardVO>(page);
		
//		List<BoardVO> list = bService.list();		
		to = bService.list(to);		
		model.addAttribute("to", to);
		model.addAttribute("list", to.getList());
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {		
		bService.insert(vo);		
		return "redirect:/board/read/"+vo.getBno();
	}
	
//	우선 입력화면 생성하기 위해서 GET 먼저 작성
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		
	}
}
