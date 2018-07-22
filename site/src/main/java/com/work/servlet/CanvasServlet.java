package com.work.servlet;

import com.work.bean.Canvas;
import com.work.bean.Category;
import com.work.service.CanvasService;
import com.work.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CanvasServlet",urlPatterns = {"/CanvasListByCategoryId.do","/getImg.do","/CanvasDetail.do","/CanvasList.do"})
public class CanvasServlet extends javax.servlet.http.HttpServlet {
    private CanvasService canvasService = new CanvasService();
    private CategoryService categoryService = new CategoryService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if("/CanvasList.do".equals(request.getServletPath())){
            String page = request.getParameter("page");
            String categoryId = request.getParameter("categoryId");
            request.getSession().removeAttribute("categoryId");
            System.out.println("page:"+page);
            if(page==null || page.equals("")){
                page="1";
            }
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("page",page);
            if(categoryId!=null && !categoryId.equals("")){
                param.put("categoryId",categoryId);
            }
            List<Canvas> list = canvasService.getCanvas(param);
            List<Category> categories = categoryService.getCategoryList();
            int count = canvasService.getCanvasCount(param);

            request.getSession().setAttribute("P",page);
            request.getSession().setAttribute("L",count%6 ==0 ? count/6 : count/6+1);
            request.getSession().setAttribute("categorys",categories);
            System.out.println(count);
            request.getSession().setAttribute("canvasList",list);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else if("/CanvasDetail.do".equals(request.getServletPath())){
            String id = request.getParameter("id");
            Map<String,Object> param  = new HashMap<String, Object>();
            param.put("id",id);
            List<Canvas> list = canvasService.getCanvas(param);
            request.getSession().setAttribute("canvas",list.get(0));
            request.getRequestDispatcher("/detail.jsp").forward(request,response);
        }else if("/getImg.do".equals(request.getServletPath())){
            String id = request.getParameter("id");
            Map<String,Object> param  = new HashMap<String, Object>();
            param.put("id",id);
            Canvas canvas = canvasService.getCanvas(param).get(0);
            try {
                response.setContentType("multipart/from-data");
                if(null != canvas && null != canvas.getSmallImg()){
                    InputStream in = new ByteArrayInputStream(canvas.getSmallImg());
                    ServletOutputStream out = response.getOutputStream();
                    byte[] b = new byte[1024];
                    int length = in.read(b);
                    while (length != -1){
                        out.write(b);
                        length = in.read(b);
                    }
                    out.flush();
                    out.close();
                    in.close();
                    response.flushBuffer();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }else if("/CanvasListByCategoryId.do".equals(request.getServletPath())){
            String categoryId = request.getParameter("categoryId");
            String page = request.getParameter("page");
            if(page==null || page.equals("")){
                page="1";
            }
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("categoryId",categoryId);
            param.put("page",page);
            List<Canvas> list = canvasService.getCanvas(param);
            int count = canvasService.getCanvasCount(param);
            request.getSession().setAttribute("canvasList", list);
            request.getSession().setAttribute("P",page);
            request.getSession().setAttribute("L",count%6 ==0 ? count/6 : count/6+1);
            request.getSession().setAttribute("categoryId",categoryId);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }
}
