package hello.board.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
    private int bno;
    private String title;
    private String content;
    private String userid;
}
