package thymeleaf.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import decorator.constant.Constant;
import decorator.entity.Category;
import decorator.service.CategoryService;
import decorator.service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = {"/category", "/category/create", "/category/delete", "/category/edit"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 50,      // 50MB
    maxRequestSize = 1024 * 1024 * 100   // 100MB
)
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String uri = req.getRequestURI();

        if (uri.endsWith("/create")) {
            req.getRequestDispatcher("/WEB-INF/views/category/create.jsp").forward(req, resp);
        } else if (uri.endsWith("/edit")) {
            Long id = Long.parseLong(req.getParameter("id"));
            Category category = categoryService.findById(id);
            req.setAttribute("category", category);
            req.getRequestDispatcher("/WEB-INF/views/category/edit.jsp").forward(req, resp);
        } else {
            req.setAttribute("list", categoryService.list());
            req.getRequestDispatcher("/WEB-INF/views/category/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.endsWith("/create")) {
            // Lấy dữ liệu từ form
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String type = req.getParameter("type");
            boolean active = req.getParameter("active") != null;

            // Upload icon
            Part iconPart = req.getPart("icon");
            String iconFile = saveFile(iconPart, req, "icon");

            // Upload image
            Part imagePart = req.getPart("image");
            String imageFile = saveFile(imagePart, req, "image");

            // Tạo Category
            Category c = new Category();
            c.setName(name);
            c.setDescription(description);
            c.setType(type);
            c.setActive(active);
            c.setIcon(iconFile);
            c.setImage(imageFile);

            // Lưu vào DB
            categoryService.create(c);

            resp.sendRedirect(req.getContextPath() + "/category");
        } else if (uri.endsWith("/delete")) {
            try {
                categoryService.delete(Long.parseLong(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/category");
            } catch (Exception e) {
                req.setAttribute("error", "Không thể xóa danh mục vì đang có video sử dụng!");
                List<Category> list = categoryService.list();
                req.setAttribute("categories", list);
                req.getRequestDispatcher(Constant.CATEGORY_LIST).forward(req, resp);
            }
        }
    }

    // Hàm saveFile lưu file vào thư mục uploads
    private String saveFile(Part part, HttpServletRequest req, String folderName) throws IOException {
        if (part == null || part.getSize() == 0) return null;

        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String uploadPath = req.getServletContext().getRealPath("/") + "uploads" + File.separator + folderName;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        part.write(uploadPath + File.separator + fileName);
        return "uploads/" + folderName + "/" + fileName; // lưu path để show ảnh
    }
}