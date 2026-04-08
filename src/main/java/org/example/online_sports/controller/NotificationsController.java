package org.example.online_sports.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.request.ReqGroup;
import org.example.online_sports.request.ReqNotifications;
import org.example.online_sports.response.ResGroup;
import org.example.online_sports.response.ResNotifications;
import org.example.online_sports.service.GroupsService;
import org.example.online_sports.service.NotificationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/notifications")
public class NotificationsController {
    private final NotificationsService notificationsService;

    @PostMapping("/save")
    public ApiResponse saveNotifications(@RequestBody ReqNotifications reqNotifications) {
        return notificationsService.saveNotifications(reqNotifications);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteNotifications(@PathVariable Long id) {
        return notificationsService.deleteNotifications(id);
    }

    @GetMapping("/list")
    public List<ResNotifications> getAllNotifications() {
        return notificationsService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public ResNotifications getOneNotifications(@PathVariable Long id) {
        return notificationsService.getOneNotifications(id);
    }

    @PutMapping("/{id}")
    public ApiResponse updateNotifications(@PathVariable Long id, @RequestBody ReqNotifications reqNotifications) {
        return notificationsService.updateNotifications(id, reqNotifications);
    }
}
