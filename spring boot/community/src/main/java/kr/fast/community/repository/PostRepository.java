package kr.fast.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{


	Page<Post> findAllByIsDeletedContaining(String isDeleted, Pageable pageble);

	Page<Post> findAllByIsDeletedAndTitleContaining(String isDeleted, String keyword, Pageable pageble);

	Page<Post> findAllByIsDeletedAndMemberIdContaining(String isDeleted, String keyword, Pageable pageble);

}
