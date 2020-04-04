package com.code.apirest.rxjava.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.apirest.rxjava.documents.Student;
import com.code.apirest.rxjava.service.StudentService;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@RestController
@RequestMapping("/api-student")
public class StudentRestController{
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Flowable<Student> listAllStudent(){
        return studentService.listStudent();
    }

    @GetMapping("/{id}")
    public Maybe<Student> getStudent(@PathVariable String id){
        return studentService.getStudent(id);
    }

    @PostMapping
    public Single<Student> saveStudent(@RequestBody Student student ){
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Single<Student> updateStudent(@RequestBody Student student , @PathVariable String id){

        return studentService.updateStudent(student, id);

    }
    @DeleteMapping("/{id}")
    public Completable deleteStudent(@PathVariable String id){
        return studentService.deleteStudent(id);
    }


}