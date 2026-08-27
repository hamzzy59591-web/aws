package kr.fast.community.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kr.fast.community.dto.MessageResponse;
import kr.fast.community.entity.Board;
import kr.fast.community.entity.Post;
import kr.fast.community.service.BoardService;
import kr.fast.community.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostController {
	
	private final PostService postService;
	private final BoardService boardService;
	
	@GetMapping("")
	public ResponseEntity<Object> get(){
		List<Post> list = postService.getPosts();
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> idGet(@PathVariable("id")int id){
		try {
			Post post = postService.getPost(id);
			return ResponseEntity.ok(post);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
}
