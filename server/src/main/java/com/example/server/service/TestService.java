package com.example.server.service;

import com.example.server.domain.Test;
import com.example.server.domain.TestExample;
import com.example.server.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * date: 2020/11/2 16:08
 *
 * @author 896951384
 * @since JDK 1.8
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Test> selectAll() {
        TestExample testExample = new TestExample();
        List<Test> list = testMapper.selectByExample(testExample);
        return list;
    }
}
