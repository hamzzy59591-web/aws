package kr.fast.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Usercontroller {
	
	@GetMapping("/user/nickname")
	@ResponseBody // 리턴값을 그대로 전달 (뷰리졸버 거치지 말고)
	public String userNickname() {
		// 로그인한 회원 닉네임을 가져왔다 치고
		return "홍길동";
	}
	
	@GetMapping("/user/Signup")
	@ResponseBody // 리턴값을 그대로 전달 (뷰리졸버 거치지 말고)
	public String SignupUser(SignupDTO dto) {
		System.out.println(dto);
		
		return "회원가입이 완료되었습니다.";
	}

}
