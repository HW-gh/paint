package com.work.service;

import com.work.bean.Canvas;
import com.work.mapper.CanvasMapper;
import com.work.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class CanvasService {
    public List<Canvas> getCanvas(Map<String,Object> param){
        SqlSession session = MybatisUtil.openSession();
        try {
            CanvasMapper mapper = session.getMapper(CanvasMapper.class);
            return mapper.getCanvas(param);
        }finally {
            session.close();
        }
    }
    public int getCanvasCount(Map<String,Object> param){
        SqlSession session = MybatisUtil.openSession();
        try {
            CanvasMapper mapper = session.getMapper(CanvasMapper.class);
            return mapper.getCanvasCount(param);
        }finally {
            session.close();
        }
    }

    public void addCanvas(Canvas canvas) {
        SqlSession session = MybatisUtil.openSession();
        try {
            CanvasMapper mapper = session.getMapper(CanvasMapper.class);
            System.out.println("Service:"+canvas);
            mapper.addCanvas(canvas);
            session.commit();
        }finally {
            session.close();
        }
    }

    public void deleteCanvasByid(long id){
        SqlSession session = MybatisUtil.openSession();
        try {
            CanvasMapper mapper = session.getMapper(CanvasMapper.class);
            mapper.deleteCanvasByid(id);
            session.commit();
        }finally {
            session.close();
        }
    }
    public void updateCanvasById(Canvas canvas){
        SqlSession session = MybatisUtil.openSession();
        try {
            CanvasMapper mapper = session.getMapper(CanvasMapper.class);
            mapper.updateCanvasById(canvas);
            session.commit();
        }finally {
            session.close();
        }
    }
}
