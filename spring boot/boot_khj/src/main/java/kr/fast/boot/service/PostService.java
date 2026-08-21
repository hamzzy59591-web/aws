package kr.fast.boot.service;

import kr.fast.boot.repository.BoardRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.fast.boot.dto.BoardDTO;
import kr.fast.boot.dto.PostDTO;
import kr.fast.boot.entity.Board;
import kr.fast.boot.entity.Member;
import kr.fast.boot.entity.Post;
import kr.fast.boot.repository.PostRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly=true)
@AllArgsConstructor
public class PostService {
	
	private final BoardRepository boardRepository;
	private final PostRepository postRepository;


	@Transactional
	public void insertPost(PostDTO dto) {
		// 입력값 예외처리
		if(dto == null || !dto.checkTitleValid()){
			throw new IllegalArgumentException("제목을 입력하세요.");
		}
		if(dto == null || !dto.checkContentValid()){
			throw new IllegalArgumentException("내용을 입력하세요.");
		}
		//게시판 번호 체크
		if(!boardRepository.existsById(dto.boardId())){
			throw new IllegalArgumentException("잘못된 게시판입니다.");
		}
		
		//post엔티티 생성
		System.out.println(dto);
		Post post = new Post(dto.title(),dto.content(),dto.writer(),dto.boardId());
		
		//레포야 엔티티 저장해
		postRepository.save(post);
	}


	public List<Post> getPostList() {
		// List<Post> list = postRepository.findAll(); // 최신 게시글이 제일 마지막
		List<Post> list = postRepository.findAllByOrderByIdDesc(); // 최신 게시글이 제일 위
		
		return list;
	}
	
	@Transactional
	public Post getPost(int id) {
		
		Post Post
		= postRepository.findById(id)
		.orElseThrow(()-> new IllegalArgumentException("게시판이 존재하지 않습니다."));
		return post;
	}

}
