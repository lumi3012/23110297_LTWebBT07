package thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import thymeleaf.entity.Category;
import thymeleaf.entity.Video;
import thymeleaf.service.CategoryService;
import thymeleaf.service.VideoService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/video")
    public String listVideos(Model model) {
        List<Video> list = videoService.findAll();
        model.addAttribute("videos", list);
        return "video/list";
    }

    @GetMapping("/video/create")
    public String createVideoForm(Model model) {
        model.addAttribute("categories", categoryService.list());
        return "video/create";
    }

    @GetMapping("/video/edit")
    public String editVideoForm(@RequestParam("id") Long id, Model model) {
        Video v = videoService.findById(id);
        model.addAttribute("video", v);
        model.addAttribute("categories", categoryService.list());
        return "video/edit";
    }

    @GetMapping("/video/delete")
    public String deleteVideo(@RequestParam("id") Long id) {
        videoService.delete(id);
        return "redirect:/video";
    }

    @GetMapping("/video/search")
    public String searchVideos(@RequestParam("keyword") String keyword, Model model) {
        List<Video> list = videoService.search(keyword);
        model.addAttribute("videos", list);
        return "video/list";
    }

    @PostMapping("/video/create")
    public String createVideo(@RequestParam String title,
                              @RequestParam String description,
                              @RequestParam String genre,
                              @RequestParam Boolean active,
                              @RequestParam Long categoryId,
                              @RequestParam("thumbnail") MultipartFile thumbnail,
                              @RequestParam("videoFile") MultipartFile videoFile,
                              Model model) {
        try {
            String thumbPath = saveFile(thumbnail, "uploads/thumbnails");
            String videoPath = saveFile(videoFile, "uploads/videos");
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
            return "redirect:/video";
        } catch (Exception e) {
            model.addAttribute("error", "Tạo video thất bại: " + e.getMessage());
            model.addAttribute("categories", categoryService.list());
            return "video/create";
        }
    }

    private String saveFile(MultipartFile file, String folder) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadDir = folder;
            new File(uploadDir).mkdirs();
            String fileName = file.getOriginalFilename();
            file.transferTo(new File(uploadDir + File.separator + fileName));
            return folder + "/" + fileName;
        }
        return null;
    }
}