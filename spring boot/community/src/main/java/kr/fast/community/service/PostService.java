package kr.fast.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.fast.community.entity.File;
import jakarta.annotation.PostConstruct;
import kr.fast.community.dto.CommentRequest;
import kr.fast.community.dto.MessageResponse;
import kr.fast.community.dto.PageResponse;
import kr.fast.community.dto.PostRequest;
import kr.fast.community.entity.Board;
import kr.fast.community.entity.Comment;
import kr.fast.community.entity.Post;
import kr.fast.community.repository.BoardRepository;
import kr.fast.community.repository.CommentRepository;
import kr.fast.community.repository.FileRepository;
import kr.fast.community.repository.MemberRepository;
import kr.fast.community.repository.PostRepository;
import kr.fast.community.security.CustomUserDetails;
import kr.fast.community.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	private final FileRepository fileRepository;
	private final CommentRepository commentRepository;

	@Value("${file.path}")
	private String uploadFilePath;
	
	@PostConstruct
	public void init() {
		java.io.File dir = new java.io.File(uploadFilePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println(uploadFilePath);
	}

	
	@Transactional
	public PageResponse<Post> getPosts(String type, String keyword, Pageable pageble ) {
		Page<Post> page;
		//전체 게시글을 가져오는 코드를 작성해보세요.
		if(keyword == null || keyword.isBlank()) {
			page =  postRepository.findAllByIsDeletedContaining("N", pageble);
		}
		else if("title".equals(type)) {
			page = postRepository.findAllByIsDeletedAndTitleContaining("N", keyword,pageble);
		}
		
		else if("writer".equals(type)) {
			page = postRepository.findAllByIsDeletedAndMemberIdContaining("N", keyword,pageble);
		}
		else {
			page = postRepository.findAllByIsDeletedContaining("N",pageble);
		} 
		return new PageResponse<Post>(page, 3);
	}

	public Post getPost(int id) {
		
		Post post = postRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("존재하지 않은 게시글입니다."));
		if(post.getIsDeleted().equals("Y")) {
			throw new IllegalArgumentException("삭제된 게시글입니다.");
		}
		return post;
	}

	public MessageResponse insertPost(PostRequest request, CustomUserDetails userDetails, List<MultipartFile> files) {
		
		if(userDetails == null) {
			throw new IllegalArgumentException("로그인이 필요한 서비스입니다.");
		}
		
		if(request == null 
			|| !request.validTitle()
			|| !request.validContent()
			|| !request.validBoardId()) {
			throw new IllegalArgumentException("입력되지 않은 항목이 있습니다.");
		}
		//유효한 게시판인지 체크
		Board board = boardRepository.findById(request.boardId())
				.orElseThrow(()->new IllegalArgumentException("없는 게시판 입니다."));
		
		if(board == null) {
			throw new IllegalArgumentException("없는 게시판 입니다.");
		}
		
		
		//사용자 체크
		boolean existsUser = memberRepository.existsById(userDetails.getUsername());
		if(!existsUser) {
			throw new IllegalArgumentException("등록되지 않은 사용자입니다.");
		}
		//게시글 엔티티 생성
		Post post = request.toPost(board, userDetails.getUsername());
		//게시글 등록
		Post savedPost = postRepository.save(post);
		
		if(files != null) {
			for(MultipartFile file : files) {
				String savedName = FileUtils.saveFile(uploadFilePath, file);
				String originalName = file.getOriginalFilename();
				File fileEntity = new File(savedName, originalName, savedPost.getId()); 
				fileRepository.save(fileEntity);
			}
			
		}
		return new MessageResponse(true, "게시글을 등록했습니다.");
	}


	public List<File> getFiles(int id) {
		
		return fileRepository.findAllByPostId(id);
	}


	public MessageResponse insertComment(int id, CommentRequest request, CustomUserDetails userDetails) {
		
		//게시글 존재 확인
		Post post = postRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("게시글이 존재하지 않습니다."));
		
		if(post == null || post.getIsDeleted().equals("Y")) {
			throw new RuntimeException("게시글이 존재하지 않습니다.");
		}
		//사용자 확인
		if(userDetails == null || userDetails.getUsername().isEmpty()) {
			throw new RuntimeException("로그인이 필요한 서비스입니다.");	
		}
		
		//댓글 내용 확인
		if(request == null || request.content()==null || request.content().isBlank()) {
			throw new RuntimeException("댓글을 입력하세요.");	
		}
		
		//댓글 등록
		return null;
	}

	
}
