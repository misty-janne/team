package kr.co.ca;

import java.io.File;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.utils.Utils;

@Controller
public class UploadAjaxController {
	private String uploadPath = "C:"+File.separator+"upload";
	
	@ResponseBody //레스트컨트롤러가 아닌 컨트롤러 어노테이션이므로
	@RequestMapping(value = "/uploadajax", 
					method = RequestMethod.POST,
					produces = "text/plain;charset=UTF-8") //한글인코딩	
	public String uploadajax(MultipartHttpServletRequest request) throws Exception {
		
		MultipartFile file = request.getFile("file");
		
		String originalName = file.getOriginalFilename();
		
		String saveFileName = Utils.saveFile(originalName, file, uploadPath);
//									└안에 파일위치가 들어있음
		
		return saveFileName;
	}
	
	@RequestMapping(value = "/uploadajax", method = RequestMethod.GET)
	public void uploadajax() {
		/*
		String[] paths = Utils.makeDirName();
		
		File f = new File(uploadPath + paths[2]);
//										└"C:\\upload\2020\07\09\"
		
		if (f.exists()) {
//			└위의 폴더가 존재하는지
			return; //존재하면 넘어가기
		}
		
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
//										└"C:\\upload\"
			if (!dirPath.exists()) {
//				└위의 폴더가 존재하지않는지
				dirPath.mkdir(); //존재하지않으면 만들어주기
			}
		} */

	}

}
