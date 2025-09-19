package thymeleaf.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import decorator.entity.Category;
import decorator.entity.Video;
import decorator.service.CategoryService;
import decorator.service.VideoService;
import decorator.service.impl.CategoryServiceImpl;
import decorator.service.impl.VideoServiceImpl;


@WebServlet(urlPatterns = {"/video", "/video/create", "/video/edit", "/video/delete", "/video/search"})
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 50,      // 50MB
	    maxRequestSize = 1024 * 1024 * 100   // 100MB
)

public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final VideoService videoService = new VideoServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        if (path.equals("/video")) {
            List<Video> list = videoService.findAll();
            req.setAttribute("videos", list);
            req.getRequestDispatcher("/WEB-INF/views/video/list.jsp").forward(req, resp);

        } else if (path.equals("/video/create")) {
            req.setAttribute("categories", categoryService.list());
            req.getRequestDispatcher("/WEB-INF/views/video/create.jsp").forward(req, resp);

        } else if (path.equals("/video/edit")) {
            Long id = Long.valueOf(req.getParameter("id"));
            Video v = videoService.findById(id);
            req.setAttribute("video", v);
            req.setAttribute("categories", categoryService.list());
            req.getRequestDispatcher("/WEB-INF/views/video/edit.jsp").forward(req, resp);

        } else if (path.equals("/video/delete")) {
            Long id = Long.valueOf(req.getParameter("id"));
            videoService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/video");

        } else if (path.equals("/video/search")) {
            String keyword = req.getParameter("keyword");
            List<Video> list = videoService.search(keyword);
            req.setAttribute("videos", list);
            req.getRequestDispatcher("/WEB-INF/views/video/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/video/create".equals(action)) {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String genre = req.getParameter("genre");
            Boolean active = Boolean.valueOf(req.getParameter("active"));
            Long categoryId = Long.parseLong(req.getParameter("categoryId"));

            // upload thumbnail
            Part thumbPart = req.getPart("thumbnail");
            String thumbPath = saveFile(thumbPart, "/uploads/thumbnails");

            // upload video file
            Part videoPart = req.getPart("videoFile");
            String videoPath = saveFile(videoPart, "/uploads/videos");

            Category cat = categoryService.findById(categoryId);

            Video v = new Video();
            v.setTitle(title);
            v.setDescription(description);
            v.setGenre(genre);
            v.setActive(active);
            v.setThumbnail(thumbPath);
            v.setPath(videoPath);
            v.setCategory(cat);

            videoService.create(v);
            resp.sendRedirect(req.getContextPath() + "/video");
        }
    }

    private String saveFile(Part part, String folder) throws IOException {
        if (part != null && part.getSize() > 0) {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath(folder);
            new File(uploadDir).mkdirs();
            part.write(uploadDir + File.separator + fileName);
            return folder.substring(1) + "/" + fileName;
        }
        return null;
    }

}
