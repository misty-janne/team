package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberDTO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired //어딘가 객체가 있다 --null이 아님 (service 어노테이션 등 ...)
	private MemberService memberService;
//			└인터페이스 구현

	//delete
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		memberService.delete(id);
		return "redirect:/member/list";
	}
	
	
	//update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MemberDTO dto) {
		memberService.update(dto);
		return "redirect:/member/list";
	}	
	
	
	//updateui
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//									  └동적 데이터
	public String updateui(@PathVariable("id") String id, Model model) {
		MemberDTO dto = memberService.updateui(id);
		model.addAttribute("dto", dto);
		return "member/update";
	}

	
	//selectById
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(String id, Model model) {
		MemberDTO dto = memberService.read(id);
		model.addAttribute("dto", dto); //setAttribute 역할
	}
	
	
	//select
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<MemberDTO> list = memberService.list();
		model.addAttribute("list", list);
	}
	
	
	
	//insert	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberDTO dto) {
		
		memberService.insert(dto);
//		└컨트롤러는 서비스를 거쳐서 dao로 연결된다
		
		
		return "redirect:/member/list";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "member/insert";
	}
	
}
