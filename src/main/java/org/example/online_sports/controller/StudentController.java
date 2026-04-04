package org.example.online_sports.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.request.ReqStudent;
import org.example.online_sports.response.ResStudent;
import org.example.online_sports.service.StudentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/student")
public class StudentController {
    private final StudentsService studentsService;

    @PostMapping("/save")
    public ApiResponse saveStudent(@RequestBody ReqStudent reqStudent) {
        return studentsService.saveStudent(reqStudent);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteStudent(@PathVariable Long id) {
        return studentsService.deleteStudent(id);
    }

    @GetMapping("/list")
    public List<ResStudent> getAllStudents() {
        return studentsService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResStudent getOneStudent(@PathVariable Long id) {
        return studentsService.getOneStudent(id);
    }

    @PutMapping("/{id}")
    public ApiResponse updateStudents(@PathVariable Long id, @RequestBody ReqStudent reqStudent) {
        return studentsService.updateStudent(id, reqStudent);
    }
}