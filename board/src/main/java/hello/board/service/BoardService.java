package hello.board.service;

import hello.board.dao.Board;
import hello.board.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<Board> selectPosts() throws Exception {
        List<Board> list = boardMapper.selectPosts();
        return list;
    }

    public Board selectPost(int bno) {
        Board oneBoard = boardMapper.selectPost(bno);
        return oneBoard;
    }

    public String selectPostContent(int bno) {
        String postContent = boardMapper.selectPostContent(bno);
        return postContent;
    }

}
