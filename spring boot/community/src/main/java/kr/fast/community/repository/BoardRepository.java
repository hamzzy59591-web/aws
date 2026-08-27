package kr.fast.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.fast.community.entity.Board;
import kr.fast.community.entity.Post;

public interface BoardRepository extends JpaRepository<Board, Integer >{


}
