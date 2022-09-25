package hello.board.mapper;

import hello.board.dao.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BoardMapper {
    // 게시물 조회
    List<Board> selectPosts() throws Exception;

}
