package com.example.listam.repository;

import com.example.listam.entity.CategoryEntity;
import com.example.listam.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

}
