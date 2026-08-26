package kr.fast.community.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.community.dto.MessageResponse;
import kr.fast.community.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class Authcontroller {
	
	private final AuthService authService;
	
@PostMapping("/signup")

public ResponseEntity<Object> signupPost() {
	
	
	MessageResponse messgeresponse = new MessageResponse(true, "회원가입이 완료되었습니다.");
	return ResponseEntity.ok(messgeresponse);
}

}
