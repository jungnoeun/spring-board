package hello.board.mapper;

import hello.board.domain.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@MapperScan
public interface TestMapper {
//    Test test() throws Exception;
    List<Test> test() throws Exception;
}
