package org.example.online_sports.service;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.entity.Groups;
import org.example.online_sports.entity.Notifications;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.repository.GroupsRepository;
import org.example.online_sports.repository.NotificationsRepository;
import org.example.online_sports.request.ReqGroup;
import org.example.online_sports.request.ReqNotifications;
import org.example.online_sports.response.ResGroup;
import org.example.online_sports.response.ResNotifications;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NotificationsService {
    // Bu to'liq CRUD qism✅
    private final NotificationsRepository notificationsRepository;

    public ApiResponse saveNotifications(ReqNotifications reqNotifications) {
        if (notificationsRepository.existsByTitle(reqNotifications.getTitle())) {
            return ApiResponse.builder()
                    .message("Notifications with name " + reqNotifications.getTitle() + " already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        Notifications notifications = Notifications.builder()
                .title(reqNotifications.getTitle())
                .message(reqNotifications.getMessage())
                .build();
        notificationsRepository.save(notifications);
        return ApiResponse.builder()
                .message("Notifications saved")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
    }

    public ApiResponse updateNotifications(Long id, ReqNotifications reqNotifications) {
        boolean exists = notificationsRepository.existsByTitleAndIdNot(reqNotifications.getTitle(), id);
        if (!exists) {
            Optional<Notifications> byId = notificationsRepository.findById(id);
            if (byId.isPresent()) {
                Notifications notifications = byId.get();
                notifications.setTitle(reqNotifications.getTitle());
                notifications.setMessage(reqNotifications.getMessage());
                notificationsRepository.save(notifications);
                return ApiResponse.builder()
                        .message("Notifications updated")
                        .success(true)
                        .status(HttpStatus.OK)
                        .build();
            }
            return ApiResponse.builder()
                    .message("Notifications not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ApiResponse.builder()
                .message("Notifications already exists")
                .success(false)
                .status(HttpStatus.CONFLICT)
                .build();
    }

    public ApiResponse deleteNotifications(Long id) {
        if (!notificationsRepository.existsById(id)) {
            return ApiResponse.builder()
                    .message("Notifications not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        notificationsRepository.deleteById(id);
        return ApiResponse.builder()
                .message("Notifications deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public List<ResNotifications> getAllNotifications() {
        List<Notifications> notifications = notificationsRepository.findAll();
        List<ResNotifications> resNotifications = new ArrayList<>();
        for (Notifications notifications1 : notifications) {
            ResNotifications resNotifications1 = ResNotifications.builder()
                    .title(notifications1.getTitle())
                    .message(notifications1.getMessage())
                    .build();

            resNotifications.add(resNotifications1);
        }
        return resNotifications;
    }

    public ResNotifications getOneNotifications(Long id) {
        Optional<Notifications> byId = notificationsRepository.findById(id);
        if (byId.isPresent()) {
            Notifications notifications = byId.get();
            ResNotifications resNotifications = ResNotifications.builder()
                    .title(notifications.getTitle())
                    .message(notifications.getMessage())
                    .build();
            return resNotifications;
        }
        return null;
    }

}