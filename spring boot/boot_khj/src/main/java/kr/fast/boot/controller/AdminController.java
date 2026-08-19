package kr.fast.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.boot.service.BoardService;
import lombok.AllArgsConstructor;

@RestController //@ResponseBody + @Controller
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
	
	private final BoardService boardService;
	
	//url : /api/admin/board
	//method : post를 처리하는 메서드를 추가
	//  리턴 : "ok"문자열을 리턴
	
	@PostMapping("/board")
	@ResponseBody
	public ResponseEntity<String> sendBoardPost() {
		return ResponseEntity.ok("ok");
		/*try {
			//서비스에게 회원가입 정보를 주면서 가입하라고 시킴
			userService.signup(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입에 성공했습니다.");
		}catch(Exception e) {
			 ResponseEntity.badRequest().body(e.getMessage());
		}*/
		
	}

}
