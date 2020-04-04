package com.danycode.apirest.rxjava;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.code.apirest.rxjava.documents.Student;
import com.code.apirest.rxjava.impl.StudentImplement;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import rx.observers.Subscribers;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Arrays;
import java.util.List;


//@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	StudentImplement studentImpl;

	@Before
	void before(){
		System.out.println("Metodo before");
		studentImpl= new StudentImplement();
	}
/*
	private static final List<String> WORDS = Arrays.asList(
       "the",
       "quick",
       "brown",
       "fox",
       "jumped",
       "over",
       "the",
       "lazy",
       "dog"
    );


	@Test
	void firstTest() {
		 // given:
		 TestObserver<String> observer = new TestObserver<>();
		 Observable<String> observable = Observable.fromIterable(WORDS)
			 .zipWith(Observable.range(1, Integer.MAX_VALUE),
				 (string, index) -> String.format("%2d. %s", index, string));
	  
		 // when:
		 observable.subscribe(observer);
	  
		 // then:
		 observer.assertComplete();
		 observer.assertNoErrors();
		 observer.assertValueCount(9);
		 assertThat(observer.values(), hasItem(" 4. fox"));
		
	}*/

	// list all students
	
	@Test
	void secondTest(){
		TestObserver<Student> subscriber = new TestObserver<>();
		Observable<Student> observable = studentImpl.listStudent().toObservable();

		System.out.println("STUDENTS");
		observable.subscribe(student -> System.out.println(student.getFullname()));

		observable.subscribe(subscriber);
		// subscriber.assertComplete();
		// observer.assertNotComplete();
		// observer.assertValueCount(6);
		subscriber.assertNoErrors();
		assertEquals(1,observable.count().blockingGet());
		
		
	}
	
	@Test
	void secondUpdateTest(){
		TestObserver<Student> subscriber = new TestObserver<>();
		Single<Student> studentSingle = studentImpl.getStudent("5dd2b68180e7991ef123919f").toSingle();
		System.out.println(studentSingle);
		
		studentSingle.subscribe(student->{
			System.out.println(student);
			student.setFullname("ashly");
			student.setNickname("AA1");
			System.out.println(student);
			studentImpl.updateStudent(student, student.getId());
		});
		
		
		//studentSingle.subscribe(subscriber);
		
		
		/*Observable<Student> observable = studentImpl.listStudent().toObservable();

		observable.subscribe(student -> System.out.println(student.getFullname()));

		observable.subscribe(subscriber);*/
		// subscriber.assertComplete();
		// observer.assertNotComplete();
		// observer.assertValueCount(6);
		subscriber.assertNoErrors();
		//assertEquals(1,observable.count().blockingGet());
		
		
	}

}
