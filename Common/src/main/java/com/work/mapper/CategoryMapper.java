package com.work.mapper;

import com.work.bean.Category;
import com.work.dynasql.CategoryDynaSql;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper {

    @Select("select * from category")
    List<Category> getCategoryList();

    @Insert("insert category(name,description,createTime,updateTime,createName) values(#{c.name},#{c.description},#{c.createTime},#{c.updateTime},#{c.createName})")
    void addCategory(@Param("c") Category category);

    @UpdateProvider(type = CategoryDynaSql.class,method = "updateCategorySql")
    void updateCategory(Category category);

    @Select("select * from category where id = #{id}")
    Category getCategoryById(@Param("id")int id);

    @Delete("delete from category where id=${id}")
    void deleteCategoryById(@Param("id")int id);
}
