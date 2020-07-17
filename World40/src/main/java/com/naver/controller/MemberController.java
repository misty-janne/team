package com.naver.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("member")
//@SessionAttributes({"id", "pw"}) //세션에 바인딩할수 있는 객체 여러개
@SessionAttributes({"login"}) //모델에 "login"이라는 키값 객체가 있으면 바인딩 시키기
public class MemberController {
	
	@Autowired //어딘가 객체가 있다 --null이 아님 (service 어노테이션 등 ...)
	private MemberService memberService;
//			└인터페이스 구현
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status) {		
		status.setComplete();		
		return "redirect:/member/list";
	}
	
	@RequestMapping(value = "/loginpost", method = RequestMethod.POST)
	public String loginpost(LoginDTO login, Model model, HttpSession session) {
		
		//dto에 로그인값 넣고
		MemberDTO dto = memberService.loginpost(login);

		if (dto != null) {			
			//not null = 로그인 됨 : 로그인값 불러오기
			model.addAttribute("login", dto);
			
			//authinter 인터셉터 에서 받을 path
			String path = (String) session.getAttribute("path");
			
			if (path != null) {
				//authinter 인터셉터에 걸렸을 경우에만
//				(비로그인 시도 때문에 로그인 화면 들어왔을 경우)
				return "redirect:"+path;
			}
//			(그외에는 일반 로그인 처리)
			
			return "redirect:/member/list"; //나중에 게시판 리스트나 메인화면으로 대체			
		} else {
			//null = 로그인 실패 : 로그인화면으로
			return "redirect:/member/login";			
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		//login jsp
	}

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
