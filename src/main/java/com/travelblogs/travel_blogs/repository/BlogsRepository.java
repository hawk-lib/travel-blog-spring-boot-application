package com.travelblogs.travel_blogs.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelblogs.travel_blogs.model.BlogModel;

@Repository
public interface BlogsRepository extends JpaRepository<BlogModel, Long>{

}
