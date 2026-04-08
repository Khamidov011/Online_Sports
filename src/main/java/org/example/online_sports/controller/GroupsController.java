package org.example.online_sports.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.request.ReqGroup;
import org.example.online_sports.response.ResGroup;
import org.example.online_sports.service.GroupsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/group")
public class GroupsController {
    private final GroupsService groupsService;

    // Guruhlarni saqlash qismi✅
    @PostMapping("/save")
    public ApiResponse saveGroup(@RequestBody ReqGroup reqGroup) {
        return groupsService.saveGroup(reqGroup);
    }

    // Guruhni o'chirish✅
    @DeleteMapping("/{id}")
    public ApiResponse deleteGroup(@PathVariable Long id) {
        return groupsService.deleteGroup(id);
    }

    // Hamma guruhlarni olish✅
    @GetMapping("/list")
    public List<ResGroup> getAllGroups() {
        return groupsService.getAllGroups();
    }

    // Bitta guruhni olish✅
    @GetMapping("/{id}")
    public ResGroup getOneGroups(@PathVariable Long id) {
        return groupsService.getOneGroup(id);
    }

    // Guruhlarni yangilash✅
    @PutMapping("/{id}")
    public ApiResponse updateGroups(@PathVariable Long id, @RequestBody ReqGroup reqGroup) {
        return groupsService.updateGroup(id, reqGroup);
    }
}
