package com.work.mapper;

import com.work.bean.Canvas;
import com.work.dynasql.CanvasDynaSql;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface CanvasMapper {

    @SelectProvider(type = CanvasDynaSql.class,method = "selectCanvasSql")
    List<Canvas> getCanvas(Map<String,Object> param);

    @SelectProvider(type = CanvasDynaSql.class,method = "selectCanvasCountSql")
    int getCanvasCount(Map<String,Object> param);

    @Insert("insert canvas(name,creator,categoryId,price,description,createTime,updateTime,smallImg,details) value(#{p.name},#{p.creator},#{p.categoryId},#{p.price},#{p.description},#{p.createTime},#{p.updateTime},#{p.smallImg},#{p.details})")
    void addCanvas(@Param("p")Canvas canvas);

    @Delete("delete from canvas where id = #{id}")
    void deleteCanvasByid(@Param("id")long id);

    @Update("update canvas set name=#{c.name},creator=#{c.creator},categoryId=#{c.categoryId},price=#{c.price},description=#{c.description},updateTime=now(),smallImg=#{c.smallImg} where id=#{c.id}")
    void updateCanvasById(@Param("c")Canvas canvas);
}
