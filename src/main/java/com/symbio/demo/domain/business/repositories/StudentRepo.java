package com.symbio.demo.domain.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.symbio.demo.domain.business.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

}
