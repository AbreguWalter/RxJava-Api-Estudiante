package com.danycode.apirest.rxjava;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.code.apirest.rxjava.documents.Student;
import com.code.apirest.rxjava.impl.StudentImplement;
import com.code.apirest.rxjava.repository.StudentRepository;
import com.code.apirest.rxjava.service.StudentService;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

// @ExtendWith(SpringExtension.class)
@SpringBootTest
class FirstTest {

    @Mock
    StudentImplement sImpl;
    
    @MockBean
    StudentRepository studentRepository;
    
    StudentService service;

    @Before
    void before() {
        sImpl = new StudentImplement();
    }
    
    public static Student student11() {
  	  Student stu = new Student();
  	  stu.setId("e7w6ewewhqe");
  	  stu.setFullname("daniel");
  	  stu.setNickname("A2");
  	  return stu;
    }

    @Test
    public void listStudentsTest(){
    	//when(studentRepository.findAll()).thenReturn(Flowable.empty());
    	when(studentRepository.findAll()).thenReturn(Flowable.just(student11(),student11()));
    	 TestSubscriber<Student> testSubscriber = new TestSubscriber<>();
    	 Flowable<Student> observable= studentRepository.findAll();
    	 testSubscriber=observable.test();
    	 testSubscriber.assertValueCount(2);
    	 testSubscriber.assertComplete();
    }

    @Test
    public void getStudentTest(){
    	when(sImpl.getStudent(any())).thenReturn(Maybe.just(student11()));
    	String id= "e7w6ewewhqe";
        Maybe<Student> observable = sImpl.getStudent(id);
    	observable.subscribe(student->System.out.println("cambiado "+student.getFullname()));
        observable.test().assertComplete();
        //observable.test().awaitDone(5, TimeUnit.SECONDS).assertNoValues();
    }
    
    @Test
    public void deleteStudentTest(){
    	
    	when(sImpl.deleteStudent("e7w6ewewhqe")).thenReturn(Completable.complete());
    	String id= "e7w6ewewhqe";
        Completable observable = sImpl.deleteStudent(id);
        observable.test().assertComplete();
    }
    
    @Test
    public void saveStudentTest() {
    	when(sImpl.saveStudent(any())).thenReturn(Single.just(student11()));
    	Single<Student> observable= sImpl.saveStudent(any());
    	
    	observable.test().assertComplete();
    	observable.test().assertValue(student-> student.getFullname() == "daniel");
    	
    }
    
    @Test
    public void updateStudentTest() {
    	when(sImpl.updateStudent(any(), any())).thenReturn(Single.just(student11()));
    	Single<Student> observable= sImpl.updateStudent(any(),any());
    	observable.test().assertComplete();
    	observable.test().assertValue(student-> student.getFullname() == "daniel");
    	
    }
    
    /*
    @Test
    public void updateStudent(){
        String id= "5dd2b68180e7991ef123919f1";
        Maybe<Student> observable = sImpl.getStudent(id);
        observable.subscribe(student->System.out.println("cambiado "+student.getFullname()));
        observable.test().awaitDone(5, TimeUnit.SECONDS).assertComplete();
        observable.test().awaitDone(5, TimeUnit.SECONDS).assertNoValues();
    }
    */
     
    
}