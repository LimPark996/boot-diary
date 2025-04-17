package org.example.bootdiary.controller;

import lombok.extern.java.Log;
import org.example.bootdiary.model.entity.Article;
import org.example.bootdiary.model.form.ArticleForm;
import org.example.bootdiary.service.ArticleService;
import org.example.bootdiary.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/article")
@Log // Logback
public class ArticleController {
    private final ArticleService articleService;
    private final FileService fileService;

    public ArticleController(ArticleService articleService, FileService fileService) {
        this.articleService = articleService;
        this.fileService = fileService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("title", "글 목록 ✏️");
        model.addAttribute("list", articleService.findAll());
        return "article/list";
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("title", "글 작성 🍎️");
        model.addAttribute("form", ArticleForm.empty());
        model.addAttribute("edit", false);
        return "article/form";
    }

    @PostMapping("/new")
    public String newArticle(ArticleForm form) {
        log.info(form.toString());
        MultipartFile file = form.file();
        if (!file.getName().isEmpty() && !file.isEmpty()) {
            // 파일의 존재로 감지하는 게 아니라 용량의 존재로 감지하는 거였다~
//            log.info("파일 있음");
            String fileType = file.getContentType();
            log.info(fileType);
            boolean isImage = fileType.startsWith("image/");
            log.info(isImage ? "이미지 O" : "이미지 X");
        } else {
            log.info("파일 없음");
        }
        return "redirect:/article";
    }
}
