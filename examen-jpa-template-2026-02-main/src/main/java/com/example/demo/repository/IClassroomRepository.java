package com.example.demo.repository;

import com.example.demo.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassroomRepository extends JpaRepository<Classroom, Long> {

}
