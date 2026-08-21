package kr.fast.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.boot.dto.PostDTO;
import kr.fast.boot.entity.Board;
import kr.fast.boot.entity.Post;
import kr.fast.boot.service.BoardService;
import kr.fast.boot.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {
	
	
	private final BoardService boardService;
	private final PostService postService;
	
	@GetMapping("/board")
	public ResponseEntity<List<Board>> boardGet(){
		
		List<Board> list = boardService.getBoardList();
		
		return ResponseEntity.ok(list);
		
	}
	
	@PostMapping("")
	public ResponseEntity<Object> boardPost(@RequestBody PostDTO dto){
		try {
			postService.insertPost(dto);
			return ResponseEntity.ok("게시글을 등록했습니다.");
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Post>> postGet() {
		List<Post> list = postService.getPostList();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Object> idget(@PathVariable("id")int id) {
		try {
			Post post = postService.getPost(id);
			return ResponseEntity.ok("ok");
		}catch(Exception e) {
			return ResponseEntity.ok("ok");
		}
	}
	
	

}
