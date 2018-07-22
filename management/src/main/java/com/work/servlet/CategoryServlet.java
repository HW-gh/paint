package com.work.servlet;

import com.work.bean.Category;
import com.work.mapper.CategoryMapper;
import com.work.service.CategoryService;
import org.apache.ibatis.annotations.Insert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns = {"/deleteCategory.do","/CategoryList.do","/addCategory.do","/addCategoryPrompt.do","/updateCategory.do","/updateCategoryPrompt.do"})
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if("/CategoryList.do".equals(request.getServletPath())){
            List<Category> categoryList = categoryService.getCategoryList();
            request.getSession().setAttribute("categorys",categoryList);
            request.getRequestDispatcher("/WEB-INF/category_list.jsp").forward(request,response);
        }else if("/addCategory.do".equals(request.getServletPath())){
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String createname = request.getParameter("createname");
            Category category = new Category(name,description,new Date(),new Date(),createname);
            System.out.println(category+name+"/"+description);
            categoryService.addCategory(category);
            request.getRequestDispatcher("/CategoryList.do").forward(request,response);
        }else if("/addCategoryPrompt.do".equals(request.getServletPath())){
            request.getRequestDispatcher("/WEB-INF/add_category.jsp").forward(request,response);
        }else if("/updateCategory.do".equals(request.getServletPath())){
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Category category = new Category();
            category.setId(Integer.parseInt(id));
            category.setName(name);
            category.setDescription(description);
            category.setUpdateTime(new Date());
            categoryService.updateCategory(category);
            request.getRequestDispatcher("/CategoryList.do").forward(request,response);
        }else if("/updateCategoryPrompt.do".equals(request.getServletPath())){
            String id = request.getParameter("id");
            Category categoryById = categoryService.getCategoryById(Integer.parseInt(id));
            request.getSession().setAttribute("category",categoryById);
            request.getRequestDispatcher("/WEB-INF/update_category.jsp").forward(request,response);
        }else if("/deleteCategory.do".equals(request.getServletPath())){
            String id =request.getParameter("id");
            categoryService.deleteCategoryById(Integer.parseInt(id));
            request.getRequestDispatcher("/CategoryList.do").forward(request,response);
        }
    }
}
