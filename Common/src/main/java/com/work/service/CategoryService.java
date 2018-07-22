package com.work.service;

import com.work.bean.Category;
import com.work.mapper.CategoryMapper;
import com.work.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

public class CategoryService {

    public List<Category> getCategoryList() {
        SqlSession session = MybatisUtil.openSession();
        try {
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            return mapper.getCategoryList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public void deleteCategoryById(int id){
        SqlSession session = MybatisUtil.openSession();
        try {
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            mapper.deleteCategoryById(id);
            session.commit();
        }finally {
            session.close();
        }
    }

    public void addCategory(Category category) {
        SqlSession session = MybatisUtil.openSession();
        try {
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            mapper.addCategory(category);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void updateCategory(Category category){
        SqlSession session = MybatisUtil.openSession();
        try {
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            mapper.updateCategory(category);
            session.commit();
        }finally {
            session.close();
        }
    }
    public Category getCategoryById(int id){
        SqlSession session = MybatisUtil.openSession();
        try {
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            return mapper.getCategoryById(id);
        }finally {
            session.close();
        }
    }
}
