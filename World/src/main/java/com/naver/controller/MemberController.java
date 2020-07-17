package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
