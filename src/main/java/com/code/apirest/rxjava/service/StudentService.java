package com.code.apirest.rxjava.service;

import com.code.apirest.rxjava.documents.Student;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface StudentService{
    Flowable<Student> listStudent();
    Single<Student> saveStudent(Student student);
    Maybe<Student> getStudent(String id);
    Single<Student> updateStudent(Student student , String id);
    Completable deleteStudent(String id);


}