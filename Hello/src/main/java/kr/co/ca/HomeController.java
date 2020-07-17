package kr.co.ca;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list() {
		
	}
	
	
	//mvc핸들러: 클라이언트의 요청을 처리해주는 메소드
	//@RequestMapping 이라는 어노테이션이 필요하다
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model, String name) {
//			└메소드의 반환형
		
		name = toKor(name);
		
		model.addAttribute("name", name);
		
		
		return "insert";
//				└WEB-INF/insert.jsp
	}
	
	private String toKor(String name) {
		
		try {
			return new String(name.getBytes("8859_1"), "URF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
