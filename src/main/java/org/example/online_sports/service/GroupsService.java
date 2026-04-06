package org.example.online_sports.service;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.entity.Groups;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.repository.GroupsRepository;
import org.example.online_sports.request.ReqGroup;
import org.example.online_sports.response.ResGroup;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class GroupsService {

    // Bu to'liq CRUD qism✅
    private final GroupsRepository groupsRepository;

    public ApiResponse saveGroup(ReqGroup reqGroup) {
        if (groupsRepository.existsByName(reqGroup.getName())) {
            return ApiResponse.builder()
                    .message("Group with name " + reqGroup.getName() + " already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Groups group = Groups.builder()
                .name(reqGroup.getName())
                .level(reqGroup.getLevel())
                .capacity(reqGroup.getCapacity())
                .build();
        groupsRepository.save(group);

        return ApiResponse.builder()
                .message("Group saved")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();

    }

    public ApiResponse updateGroup(Long id, ReqGroup reqGroup) {

        boolean exists = groupsRepository.existsByNameAndIdNot(reqGroup.getName(), id);
        if (!exists) {
            Optional<Groups> byId = groupsRepository.findById(id);
            if (byId.isPresent()) {
                Groups groups = byId.get();
                groups.setName(reqGroup.getName());
                groups.setLevel(reqGroup.getLevel());
                groups.setCapacity(reqGroup.getCapacity());
                groupsRepository.save(groups);
                return ApiResponse.builder()
                        .message("Groups updated")
                        .success(true)
                        .status(HttpStatus.OK)
                        .build();

            }
            return ApiResponse.builder()
                    .message("Group not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ApiResponse.builder()
                .message("Group already exists")
                .success(false)
                .status(HttpStatus.CONFLICT)
                .build();
    }

    public ApiResponse deleteGroup(Long id) {

        if (!groupsRepository.existsById(id)) {
            return ApiResponse.builder()
                    .message("Group not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        groupsRepository.deleteById(id);
        return ApiResponse.builder()
                .message("Group deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public List<ResGroup> getAllGroups() {

        List<Groups> groups = groupsRepository.findAll();
        List<ResGroup> resGroups = new ArrayList<>();
        for (Groups group : groups) {
            ResGroup resGroup = ResGroup.builder()
                    .name(group.getName())
                    .capacity(group.getCapacity())
                    .level(group.getLevel())
                    .build();

            resGroups.add(resGroup);
        }
        return resGroups;
    }

    public ResGroup getOneGroup(Long id) {

        Optional<Groups> byId = groupsRepository.findById(id);
        if (byId.isPresent()) {
            Groups groups = byId.get();
            ResGroup resGroup = ResGroup.builder()
                    .level(groups.getLevel())
                    .capacity(groups.getCapacity())
                    .name(groups.getName())
                    .build();
            return resGroup;
        }

        return null;

    }

}