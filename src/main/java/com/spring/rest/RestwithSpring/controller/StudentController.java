package com.spring.rest.RestwithSpring.controller;

import com.spring.rest.RestwithSpring.entities.Post;
import com.spring.rest.RestwithSpring.entities.Student;
import com.spring.rest.RestwithSpring.exceptions.StudentNotFoundException;
import com.spring.rest.RestwithSpring.services.StudentServices;
import com.spring.rest.RestwithSpring.versioning.Name;
import com.spring.rest.RestwithSpring.versioning.StudentV1;
import com.spring.rest.RestwithSpring.versioning.StudentV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class StudentController {
    @Autowired
    StudentServices studentServices;

    @Autowired
    MessageSource messageSource;

    @PostMapping(value = "/users")
    ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        studentServices.save(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(student.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/users")
    List<Student> getStudents() {
        return studentServices.getAllStudent();
    }

    @GetMapping(value = "/users/{id}", produces = "Application/xml")
    Student getStudentByID(@PathVariable Integer id) {
        Student student = studentServices.getStudentById(id);
        if (student == null) {
            throw new StudentNotFoundException("Student Not Found");
        }
        return student;
    }

    @GetMapping("/")
    String helloWorld(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        System.out.println("hello world");
        System.out.println(locale.getLanguage());
        return messageSource.getMessage("hello.morning.message", null, locale);
    }


    /*Question 7*/

    //URI Versioning
    @GetMapping("/users/V1")
    StudentV1 getStdentV1() {
        return new StudentV1("Shivam Saxena");
    }

    @GetMapping("/users/V2")
    StudentV2 getStudentV2() {
        return new StudentV2(new Name("Shivam", "Saxena"));
    }

    //Parameter Versioning

    @GetMapping(value = "/users/param", params = "version=1")
    StudentV1 getStudentParam1() {
        return new StudentV1("Shivam saxena");
    }

    @GetMapping(value = "/users/param", params = "version=2")
    StudentV2 getStudentParam2() {
        return new StudentV2(new Name("Shivam", "Saxena"));
    }

    //header versioning
    @GetMapping(value = "/users/header", headers = "API-VERSION=1")
    StudentV1 getPersonHeader1() {
        return new StudentV1("Peter Parker");
    }

    @GetMapping(value = "/users/header", headers = "API-VERSION=2")
    StudentV2 getPersonHeader2() {
        return new StudentV2(new Name("Peter", "Parker"));
    }

    /*Question 8*/
    @GetMapping("/postObject")
    public Post getPostObject() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        ResponseEntity<Post> response = restTemplate.getForEntity(url, Post.class);
        System.out.println(response.getStatusCode().toString());
        System.out.println(response.getHeaders().toString());
        return response.getBody();
    }

    @PostMapping("/createPost")
    public ResponseEntity<Post> createPost() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/json; charset=UTF-8");
        HttpEntity<Post> request = new HttpEntity<>(new Post(10000L, 10000L, "title1", "description1"), httpHeaders);
        Post post = restTemplate.postForObject(url, request, Post.class);
        System.out.println(post);
        return new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

    @PutMapping("/updatePost")
    public ResponseEntity<Post> updatePost() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/json; charset=UTF-8");
        HttpEntity<Post> request = new HttpEntity<>(new Post(1L, 100L, "title1", "description1"), httpHeaders);
        return restTemplate.exchange(url, HttpMethod.PUT, request, Post.class);
    }

    @DeleteMapping("/deletePost")
    public void deletePost() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/json; charset=UTF-8");
        HttpEntity<Post> request = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(url, HttpMethod.DELETE, request, Post.class);
    }


}
