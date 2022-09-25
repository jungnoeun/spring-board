package hello.board.controller;

import hello.board.dao.Board;
import hello.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;

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
        return "boards/posts";
    }

    void printLists(List<Board> lists) {
        Iterator<Board> iterator = lists.iterator();
        while(iterator.hasNext()) {
            Board nextBoard = iterator.next();
            System.out.println("nextBoard = " + nextBoard);
        }
    }

}
