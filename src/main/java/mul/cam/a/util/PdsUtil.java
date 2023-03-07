package mul.cam.a.util;

import java.util.Date;

public class PdsUtil {

	// 파일명 -> 변경(time) 
	
	// myfile.txt -> 34234234312.txt
	public static String getNewFileName(String filename) {
		
		String newfilename = "";
		String fpost = ""; // 파일명의 위치
		
		// 확장자 명이 있음
		if(filename.indexOf('.') >= 0) {
			
			fpost = filename.substring(filename.indexOf('.')); // .txt만 잘라오기
			newfilename = new Date().getTime() + fpost; // 숫자와 .txt가 합쳐짐 ex 21314413.txt
			
			
		}else { // 확장자 명이 없음
			
			newfilename = new Date().getTime() + ".back";
			
		}
		
		return newfilename;
	}
	
}
