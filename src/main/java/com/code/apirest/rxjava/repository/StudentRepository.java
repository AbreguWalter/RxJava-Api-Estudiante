package com.code.apirest.rxjava.repository;

import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

import com.code.apirest.rxjava.documents.Student;

@Repository
public interface StudentRepository extends RxJava2CrudRepository<Student,String>{



}