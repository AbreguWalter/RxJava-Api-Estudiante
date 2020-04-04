package com.code.apirest.rxjava.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.apirest.rxjava.documents.Student;
import com.code.apirest.rxjava.repository.StudentRepository;
import com.code.apirest.rxjava.service.StudentService;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Service
public class StudentImplement implements StudentService{

    @Autowired
    private StudentRepository studentRepo;
    

    @Override
    public Flowable<Student> listStudent(){
        return studentRepo.findAll();
    }

    @Override
    public Single<Student> saveStudent(Student student){
        return studentRepo.save(student);

    }

    @Override
    public Maybe<Student> getStudent(String id){
    	Maybe<Student> stu = studentRepo.findById(id);
    	if(stu.hashCode()!=0) {
    		return studentRepo.findById(id);
    	}else {
    		return Maybe.empty();
    	}
        
    }

    @Override
    public Single<Student> updateStudent(Student student , String id){
        Maybe<Student> sMaybe = studentRepo.findById(id);
        return sMaybe.map(s-> {
            s.setFullname(student.getFullname());
            s.setNickname(student.getNickname());
            return s;
        })
        .toSingle()
        .flatMap(s-> studentRepo.save(s)); 
        
    }

    @Override
    public Completable deleteStudent(String id){
        // Maybe<Student> sMaybe = studentRepo.findById(id);
        // return sMaybe.toSingle().flatMapCompletable(s-> studentRepo.delete(s)); 
        return studentRepo.deleteById(id);
        
        
    }


}