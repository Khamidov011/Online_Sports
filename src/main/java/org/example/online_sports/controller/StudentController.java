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

    // O'quvchi saqlash✅
    @PostMapping("/save")
    public ApiResponse saveStudent(@RequestBody ReqStudent reqStudent) {
        return studentsService.saveStudent(reqStudent);
    }

    // O'quvchini o'chirish✅
    @DeleteMapping("/{id}")
    public ApiResponse deleteStudent(@PathVariable Long id) {
        return studentsService.deleteStudent(id);
    }

    // hamma o'quvchilarni olish✅
    @GetMapping("/list")
    public List<ResStudent> getAllStudents() {
        return studentsService.getAllStudents();
    }

    // Bitta o'quvchini olish✅
    @GetMapping("/{id}")
    public ResStudent getOneStudent(@PathVariable Long id) {
        return studentsService.getOneStudent(id);
    }

    // O'quvchilarni yangilash✅
    @PutMapping("/{id}")
    public ApiResponse updateStudents(@PathVariable Long id, @RequestBody ReqStudent reqStudent) {
        return studentsService.updateStudent(id, reqStudent);
    }
}