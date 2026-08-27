package kr.fast.community.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.fast.community.entity.Post;
import kr.fast.community.repository.PostRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	@Transactional
	public List<Post> getPosts( ) {
		//전체 게시글을 가져오는 코드를 작성해보세요.
		
		List<Post> list = 
		postRepository.findAllByIsDeletedOrderByIdDesc("N");
		return list;
	}

	public Post getPost(int id) {
		
		Post post = postRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("존재하지 않은 게시글입니다."));
		if(post.getIsDeleted().equals("Y")) {
			throw new IllegalArgumentException("삭제된 게시글입니다.");
		}
		return post;
	}
	
}
