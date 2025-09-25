package thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import thymeleaf.entity.Category;
import thymeleaf.service.CategoryService;

import java.io.File;
import java.io.IOException;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String listCategories(Model model) {
        model.addAttribute("list", categoryService.list());
        return "category/list";
    }

    @GetMapping("/category/create")
    public String createCategoryForm() {
        return "category/create";
    }

    @GetMapping("/category/edit")
    public String editCategoryForm(@RequestParam("id") Long id, Model model) {
        Category cate = categoryService.findById(id);
        model.addAttribute("cate", cate);
        return "category/edit";
    }

    @PostMapping("/category/create")
    public String createCategory(@RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam String type,
                                 @RequestParam(required = false) Boolean active,
                                 @RequestParam("icon") MultipartFile icon,
                                 @RequestParam("image") MultipartFile image,
                                 Model model) {
        try {
            String iconFile = saveFile(icon, "uploads/icon");
            String imageFile = saveFile(image, "uploads/image");
            Category c = new Category();
            c.setName(name);
            c.setDescription(description);
            c.setType(type);
            c.setActive(active != null ? active : false);
            c.setIcon(iconFile);
            c.setImage(imageFile);
            categoryService.create(c);
            return "redirect:/category";
        } catch (Exception e) {
            model.addAttribute("error", "Tạo category thất bại: " + e.getMessage());
            return "category/create";
        }
    }

    @PostMapping("/category/edit")
    public String editCategory(@RequestParam Long id,
                               @RequestParam String name,
                               @RequestParam String description,
                               @RequestParam String type,
                               @RequestParam(required = false) Boolean active,
                               @RequestParam("icon") MultipartFile icon,
                               @RequestParam("image") MultipartFile image,
                               Model model) {
        try {
            Category cate = categoryService.findById(id);
            cate.setName(name);
            cate.setDescription(description);
            cate.setType(type);
            cate.setActive(active != null ? active : false);
            if (icon != null && !icon.isEmpty()) {
                String iconFile = saveFile(icon, "uploads/icon");
                cate.setIcon(iconFile);
            }
            if (image != null && !image.isEmpty()) {
                String imageFile = saveFile(image, "uploads/image");
                cate.setImage(imageFile);
            }
            categoryService.update(cate);
            return "redirect:/category";
        } catch (Exception e) {
            model.addAttribute("error", "Cập nhật category thất bại: " + e.getMessage());
            return "category/edit";
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