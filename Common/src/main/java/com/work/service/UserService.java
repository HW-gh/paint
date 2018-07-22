package com.work.service;

import com.work.bean.User;
import com.work.mapper.UserMapper;
import com.work.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UserService {
    public User getUserByName(String name){
        SqlSession session = MybatisUtil.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserByName(name);
        }finally {
            session.close();
        }
    }
}
