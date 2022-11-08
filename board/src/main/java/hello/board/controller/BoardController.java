package hello.board.controller;

import hello.board.dao.Board;
import hello.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/posts")
    public String selectPosts(Model model) throws Exception {
        //List<Board> list = boardService.selectPosts();
        //printLists(list);
        model.addAttribute("lists",boardService.selectPosts());
        model.addAttribute("urlTest", "urlTest");
        return "boards/posts";
    }


    @GetMapping("/posts/{bno}")
    public String selectPost(@PathVariable int bno, Model model) {
        //Board oneBoard = boardService.selectPost(bno);
        //return oneBoard;
        //System.out.println(bno);
        //model.addAttribute("oneBoard",boardService.selectPost(bno));
        return "boards/post";
    }

    /*@ResponseBody
    @GetMapping("/posts/${bno}/all")
    public Board selectPostAll(@PathVariable int bno) {
        Board oneBoard = boardService.selectPost(bno);
        return oneBoard;
    }*/


    void printLists(List<Board> lists) {
        Iterator<Board> iterator = lists.iterator();
        while(iterator.hasNext()) {
            Board nextBoard = iterator.next();
            System.out.println("nextBoard = " + nextBoard);
        }
    }

}
