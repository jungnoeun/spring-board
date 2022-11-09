package hello.board.controller;

import hello.board.dao.Board;
import hello.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전체 post(Board) 조회하기
    @GetMapping("/posts")
    public String selectPosts(Model model) throws Exception {
        //List<Board> list = boardService.selectPosts();
        //printLists(list);
        model.addAttribute("lists",boardService.selectPosts());
        model.addAttribute("urlTest", "urlTest");
        return "boards/posts";
    }

    // 특정 post(Board) 조회하기
    @GetMapping("/posts/{bno}")
    public String selectPost(@PathVariable int bno, Model model) {
        //Board oneBoard = boardService.selectPost(bno);
        //return oneBoard;
        //System.out.println(bno);
        model.addAttribute("oneBoard",boardService.selectPost(bno));
        return "boards/post";
    }

    // Board 리스트중에 특정 Board객체의 content의 값을 가져오기 -----> 미해결
    @ResponseBody
    @GetMapping("/posts/{bno}/detail")
    public String selectPostAll(@PathVariable int bno) {
        String postContent = boardService.selectPostContent(bno);
        return postContent;
    }

    // 새로운 post(Board) 추가하기
    @GetMapping("/posts/newpost")
    public String addOnePost() {
        return "boards/addForm";
    }


    void printLists(List<Board> lists) {
        Iterator<Board> iterator = lists.iterator();
        while(iterator.hasNext()) {
            Board nextBoard = iterator.next();
            System.out.println("nextBoard = " + nextBoard);
        }
    }

}
