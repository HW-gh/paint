package com.work.servlet;

import com.work.bean.Category;
import com.work.bean.User;
import com.work.bean.Canvas;
import com.work.service.CanvasService;
import com.work.service.CategoryService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CanvasServlet", urlPatterns = {"/addCanvasPrompt.do","/updateCanvas.do","/updateCanvasPrompt.do","/deleteCanvas.do","/addCanvas.do", "/CanvasListByCategory.do", "/CanvasList.do", "/CanvasListByCategoryId.do"})
public class CanvasServlet extends HttpServlet {
    private CanvasService canvasService = new CanvasService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/CanvasList.do".equals(request.getServletPath())) {
            List<Canvas> list = canvasService.getCanvas(null);
            List<Category> categoryList = categoryService.getCategoryList();
            request.getSession().setAttribute("categorys",categoryList);
            request.getSession().setAttribute("CanvasList", list);
            request.getRequestDispatcher("/WEB-INF/canvas_list.jsp").forward(request, response);
        } else if ("/addCanvasPrompt.do".equals(request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/add_canvas.jsp").forward(request, response);
        } else if ("/CanvasListByCategoryId.do".equals(request.getServletPath())) {
            String categoryId = request.getParameter("categoryId");
            Map<String,Object> param = new HashMap<>();
            param.put("categoryId",categoryId);
            List<Canvas> list = canvasService.getCanvas(param);
            request.getSession().setAttribute("CanvasList", list);
            request.getRequestDispatcher("/WEB-INF/canvas_list.jsp").forward(request, response);
        } else if ("/addCanvas.do".equals(request.getServletPath())) {
            request.setCharacterEncoding("utf-8");
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List<FileItem> items = upload.parseRequest(request);
                    Iterator<FileItem> ite = items.iterator();
                    Canvas canvas = new Canvas();
                    User user = (User) request.getSession().getAttribute("user");
                    canvas.setCreator(user.getName());
                    canvas.setCreateTime(new Date());
                    canvas.setUpdateTime(new Date());
                    String action = request.getParameter("action");
                    while (ite.hasNext()) {
                        FileItem item = ite.next();
                        if (item.isFormField()) {
                            String fieldName = item.getFieldName();
                            if ("name".equals(fieldName)) {
                                canvas.setName(item.getString("UTF-8"));
                            } else if ("categoryId".equals(fieldName)) {
                                canvas.setCategoryId(Long.parseLong(item.getString()));
                            } else if ("price".equals(fieldName)) {
                                canvas.setPrice(Integer.parseInt(item.getString()));
                            } else if ("description".equals(fieldName)) {
                                canvas.setDescription(item.getString("UTF-8"));
                            }else if("id".equals(fieldName)){
                                canvas.setId(Long.parseLong(item.getString()));
                            }else if("details".equals(fieldName)){
                                canvas.setDetails(item.getString("utf-8"));
                            }
                        } else {
                            canvas.setSmallImg(item.get());
                        }
                    }
                    if("update".equals(action)){
                        canvasService.updateCanvasById(canvas);
                    }else {
                        canvasService.addCanvas(canvas);
                    }
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
            }
            request.getRequestDispatcher("/CanvasList.do").forward(request, response);
        }else if("/deleteCanvas.do".equals(request.getServletPath())){
            String id = request.getParameter("id");
            canvasService.deleteCanvasByid(Long.parseLong(id));
            request.getRequestDispatcher("/CanvasList.do").forward(request,response);
        }else if("/updateCanvas.do".equals(request.getServletPath())){
            request.getRequestDispatcher("/addCanvas.do?action=update").forward(request,response);
        }else if("/updateCanvasPrompt.do".equals(request.getServletPath())){
            String id = request.getParameter("id");
            Map<String,Object> param = new HashMap<>();
            param.put("id",Long.valueOf(id));
            List<Canvas> li = canvasService.getCanvas(param);
            Canvas canvas = li.get(0);
            List<Canvas> list = canvasService.getCanvas(null);
            request.getSession().setAttribute("CanvasList", list);
            request.setAttribute("canvas",canvas);
            request.getRequestDispatcher("/WEB-INF/update_canvas.jsp").forward(request,response);
        }
    }
}
