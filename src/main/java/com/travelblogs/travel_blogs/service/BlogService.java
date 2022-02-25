package com.travelblogs.travel_blogs.service;

import java.util.List;

import com.travelblogs.travel_blogs.model.BlogModel;

public interface BlogService {
	List<BlogModel> getAllBlogs();
	void saveBlog(BlogModel blogModel);
	void deleteBlog(BlogModel blogModel);
	void updateBlog(BlogModel blogModel);
}
