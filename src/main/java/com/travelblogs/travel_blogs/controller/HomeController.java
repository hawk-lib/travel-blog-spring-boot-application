package com.travelblogs.travel_blogs.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travelblogs.travel_blogs.model.BlogModel;
import com.travelblogs.travel_blogs.service.BlogService;


@Controller
public class HomeController implements ErrorController {

	@Autowired 
	private BlogService blogService;
	
	private final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();

	public HomeController() throws IOException {
		
	}
	
	
	
	//HomePage
	@GetMapping("/")
    public String homepage(Model model) {
		BlogModel blogModel = new BlogModel();
    	model.addAttribute("blog", blogModel);
    	model.addAttribute("localDate", LocalDate.now());
        return "index";
    }

	
	
	
	//Saving record
    @PostMapping("/save")
    public String save(@ModelAttribute("blog") BlogModel blogModel, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a Cover Photo.");
            return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + "/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            blogModel.setImageUrl("/image/" + fileName);
            blogService.saveBlog(blogModel);
            attributes.addFlashAttribute("message", "Added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("message", "Failed!");
        }

        // return success response
       

        return "redirect:/";
    }
	
    
    
    
    
    //Show all blogs list
	@GetMapping("/viewBlogs")
	public String viewBlogs(Model model) {
		model.addAttribute("listBlogs",blogService.getAllBlogs());
		return "viewBlogs";
	}
	
	
	
	
	
	//HTML page to make changes
	@PostMapping("/editBlog")
	public String editBlog(@ModelAttribute("blog") BlogModel blogModel, Model model) {
		model.addAttribute("imageUrl",blogModel.getImageUrl());
		model.addAttribute("id",blogModel.getId());
		model.addAttribute("name",blogModel.getName());
		model.addAttribute("date",blogModel.getDate());
		model.addAttribute("description",blogModel.getDescription());
    	model.addAttribute("localDate", LocalDate.now());
		return "editBlog";
	}

	
	
	
	//Update blog
    @PostMapping("/update")
    public String update(@ModelAttribute("blog") BlogModel blogModel, RedirectAttributes attributes) {
    	
    	 blogService.updateBlog(blogModel);
		 attributes.addFlashAttribute("message", "Updated successfully!");
    	
    return "redirect:/viewBlogs";
    }
    
    
    //Delete blog
    @PostMapping("/delete")
    public String delete(@ModelAttribute("blog") BlogModel blogModel, RedirectAttributes attributes) {
    	blogService.deleteBlog(blogModel);
		 attributes.addFlashAttribute("message", "Deleted successfully!");
		 
		 
    return "redirect:/viewBlogs";
    }
    
    
    
    
    //HTML page to view blog
    @PostMapping("/viewBlog")
    public String viewBlog(@ModelAttribute("blog") BlogModel blogModel, Model model) {
    	
    	model.addAttribute("imageUrl",blogModel.getImageUrl());
		model.addAttribute("id",blogModel.getId());
		model.addAttribute("name","By : " + blogModel.getName());
		model.addAttribute("date","Published On : " + blogModel.getDate());
		model.addAttribute("description",blogModel.getDescription());
    	model.addAttribute("localDate", LocalDate.now());
    	return "showBlog";
    }

}
