package com.work.dynasql;


import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class CanvasDynaSql {

    public String selectCanvasSql(final Map<String, Object> param) {
        String sql =  new SQL() {
            {
                SELECT("*");
                FROM("canvas");
                if (param!=null&&param.get("id")!=null) {
                    WHERE("id=#{id}");
                }
                if(param!=null&&param.get("categoryId")!=null&&!"".equals(param.get("categoryId"))){
                    WHERE("categoryId=#{categoryId}");
                }
            }
        }.toString();
        if(param!=null&&param.get("page")!=null){
            sql = sql + " limit "+(Integer.parseInt((String) param.get("page"))-1)*6+",6";
        }
        //System.out.println(sql);
        return sql;
    }

    public String selectCanvasCountSql(final Map<String, Object> param) {
        String sql =  new SQL() {
            {
                SELECT("count(*)");
                FROM("canvas");
                if (param!=null&&param.get("id")!=null) {
                    WHERE("id=#{id}");
                }
                if(param!=null&&param.get("categoryId")!=null&&!"".equals(param.get("categoryId"))){
                    WHERE("categoryId=#{categoryId}");
                }
            }
        }.toString();
        return sql;
    }
}
