package kr.fast.community.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.fast.community.dto.MessageResponse;
import kr.fast.community.dto.PageResponse;
import kr.fast.community.dto.PostRequest;
import kr.fast.community.entity.Post;
import kr.fast.community.security.CustomUserDetails;
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
	public ResponseEntity<Object> get(
			@RequestParam(required = false, defaultValue = "all", name ="type" )String type,
			@RequestParam(required = false, defaultValue = "", name ="keyword" )String keyword,
			@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC)
				Pageable pageble){
		PageResponse<Post> pageResponse = postService.getPosts(type, keyword, pageble);
		return ResponseEntity.ok(pageResponse);
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
	
	@PostMapping("")
	public ResponseEntity<Object> post(
			@RequestBody PostRequest request, //화면에서 보낸 게시글 정보
			@AuthenticationPrincipal CustomUserDetails userDetails //로그인한 회원 정보
			){
		MessageResponse ms;
		try {
			ms = postService.insertPost(request,userDetails);
		}catch(Exception e) {
			ms = new MessageResponse(false,e.getMessage());
		}
		return ResponseEntity.ok(ms);
	}
	
}
