package kr.co.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	public static String saveFile(String originalName, MultipartFile file) throws Exception {
		String newName = Utils.makeNewName(originalName);
		File target = new File("C:"+File.separator+"upload", newName);
		FileCopyUtils.copy(file.getBytes(), target);
		return newName;
		
	}
	
	//새로운 이름 지정해주는 메소드
	public static String makeNewName(String originalName) {
		UUID uid = UUID.randomUUID(); //새로운 이름 생성
		String newName = uid.toString()+"_"+originalName; //언더바로 구분
		
		return newName;
	}
	
	
	//필터로 인코딩이 안될때	
	public static String toKor(String msg) {
		try {
			return new String(msg.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null; //null포인트익센션 방지
		}
	}

}
