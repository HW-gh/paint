package com.work.dynasql;

import com.work.bean.Category;
import org.apache.ibatis.jdbc.SQL;


public class CategoryDynaSql {

    public String updateCategorySql(final Category c) {
        return new SQL() {
            {
                UPDATE("category");
                if(c.getName()!=null&&!"".equals(c.getName())) {
                    SET("name=#{name}");
                }
                if(c.getDescription()!=null&&!"".equals(c.getDescription())) {
                    SET("description=#{description}");
                }
                if(c.getUpdateTime()!=null) {
                    SET("updateTime=#{updateTime}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }


}
