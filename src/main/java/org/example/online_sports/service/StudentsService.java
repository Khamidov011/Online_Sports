package org.example.online_sports.service;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.entity.Groups;
import org.example.online_sports.entity.Students;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.repository.GroupsRepository;
import org.example.online_sports.repository.StudentsRepository;
import org.example.online_sports.request.ReqStudent;
import org.example.online_sports.response.ResStudent;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsService {
    private final StudentsRepository studentsRepository;
    private final GroupsRepository groupsRepository;

    public ApiResponse saveStudent(ReqStudent reqStudent) {
        boolean exists = studentsRepository.existsByFullNameIgnoreCase(reqStudent.getFullName());
        if (exists) {
            return ApiResponse.builder()
                    .message("Student already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        Optional<Groups> byId = groupsRepository.findById(reqStudent.getId());
        if (byId.isEmpty()) {
            return ApiResponse.builder()
                    .message("Group does not exist")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Students students = Students.builder()
                .fullName(reqStudent.getFullName())
                .age(reqStudent.getAge())
                .gender(reqStudent.getGender())
                .address(reqStudent.getAddress())
                .status(reqStudent.getStatus())
                .build();
        studentsRepository.save(students);
        return ApiResponse.builder()
                .message("Student successfully saved")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
    }

    public ApiResponse updateStudent(Long id, ReqStudent reqStudent) {
        boolean exists = studentsRepository.existsByFullNameAndIdNot(reqStudent.getFullName(), reqStudent.getId());
        if (!exists) {
            Optional<Students> byId = studentsRepository.findById(reqStudent.getId());
            if (byId.isPresent()) {
                Students students = byId.get();
                students.setFullName(reqStudent.getFullName());
                students.setAge(reqStudent.getAge());
                students.setGender(reqStudent.getGender());
                students.setAddress(reqStudent.getAddress());
                students.setStatus(reqStudent.getStatus());
                studentsRepository.save(students);
                return ApiResponse.builder()
                        .message("Student successfully updated")
                        .success(true)
                        .status(HttpStatus.OK)
                        .build();
            }
            return ApiResponse.builder()
                    .message("Student does not exist")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ApiResponse.builder()
                .message("Student already exists")
                .success(false)
                .status(HttpStatus.CONFLICT)
                .build();
    }

    public ApiResponse deleteStudent(Long id) {
        if (!studentsRepository.existsById(id)) {
            return ApiResponse.builder()
                    .message("Student does not exist")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        studentsRepository.deleteById(id);
        return ApiResponse.builder()
                .message("Student successfully deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public List<ResStudent> getAllStudents() {
        List<Students> students = studentsRepository.findAll();
        List<ResStudent> resStudents = new ArrayList<>();
        for (Students student : students) {
            ResStudent resStudent = ResStudent.builder()
                    .fullName(student.getFullName())
                    .age(student.getAge())
                    .gender(student.getGender())
                    .address(student.getAddress())
                    .status(student.getStatus())
                    .build();
            resStudents.add(resStudent);
        }
        return resStudents;
    }

    public ResStudent getOneStudent(Long id) {
        Optional<Students> byId = studentsRepository.findById(id);
        if (byId.isPresent()) {
            Students students = byId.get();
            ResStudent resStudent = ResStudent.builder()
                    .fullName(students.getFullName())
                    .age(students.getAge())
                    .gender(students.getGender())
                    .address(students.getAddress())
                    .status(students.getStatus())
                    .build();
            return resStudent;
        }
        return null;
    }
}