package com.travelblogs.travel_blogs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.travelblogs.travel_blogs.model.BlogModel;
import com.travelblogs.travel_blogs.repository.BlogsRepository;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogsRepository blogsRepository;
	
	@Override
	public List<BlogModel> getAllBlogs() {
		// TODO Auto-generated method stub
		return blogsRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
	}
	
	@Override
	public void saveBlog(BlogModel blogModel) {
		this.blogsRepository.save(blogModel);
	}
	
	@Override
	public void deleteBlog(BlogModel blogModel) {
		this.blogsRepository.deleteById(blogModel.getId());
	}

	@Override
	public void updateBlog(BlogModel blogModel) {
		this.blogsRepository.save(blogModel);		
	}

}
