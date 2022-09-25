package hello.board.service;

import hello.board.dao.Test;
import hello.board.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestMapper testMapper;
    private static final String NAMESPACE = "hello.board.TestMapper";

    @Autowired
    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }


    public List<Test> test() throws Exception {
       return testMapper.test();
    }

}
