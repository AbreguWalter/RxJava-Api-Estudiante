package com.code.apirest.rxjava.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "student1")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student{

    @Id
    private String id;

    private String fullname;

    private String nickname;
}