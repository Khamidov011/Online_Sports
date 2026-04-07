package org.example.online_sports.service;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.entity.Notifications;
import org.example.online_sports.entity.Sports_type;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.repository.NotificationsRepository;
import org.example.online_sports.repository.SportTypeRepository;
import org.example.online_sports.request.ReqSportType;
import org.example.online_sports.response.ResNotifications;
import org.example.online_sports.response.ResSportType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SportTypeService {
    // Bu to'liq CRUD qism✅
    private final SportTypeRepository sportTypeRepository;

    public ApiResponse saveSportType(ReqSportType reqSportType) {
        if (sportTypeRepository.existsByName(reqSportType.getName())) {
            return ApiResponse.builder()
                    .message("SportType with name " + reqSportType.getName() + " already exists")
                    .success(false)
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        Sports_type sport = Sports_type.builder()
                .name(reqSportType.getName())
                .category(reqSportType.getCategory())
                .description(reqSportType.getDescription())
                .minAge(reqSportType.getMinAge())
                .maxAge(reqSportType.getMaxAge())
                .build();
        sportTypeRepository.save(sport);
        return ApiResponse.builder()
                .message("SportType saved")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
    }

    public ApiResponse updateSportType(Long id, ReqSportType reqSportType) {
        boolean exists = sportTypeRepository.existsByNameAndIdNot(reqSportType.getName(), id);
        if (!exists) {
            Optional<Sports_type> byId = sportTypeRepository.findById(id);
            if (byId.isPresent()) {
                Sports_type sportsType = byId.get();
                sportsType.setName(reqSportType.getName());
                sportsType.setCategory(reqSportType.getCategory());
                sportsType.setDescription(reqSportType.getDescription());
                sportsType.setMinAge(reqSportType.getMinAge());
                sportsType.setMaxAge(reqSportType.getMaxAge());
                sportTypeRepository.save(sportsType);
                return ApiResponse.builder()
                        .message("SportType updated")
                        .success(true)
                        .status(HttpStatus.OK)
                        .build();
            }
            return ApiResponse.builder()
                    .message("SportType not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ApiResponse.builder()
                .message("SportType already exists")
                .success(false)
                .status(HttpStatus.CONFLICT)
                .build();
    }

    public ApiResponse deleteSportType(Long id) {
        if (!sportTypeRepository.existsById(id)) {
            return ApiResponse.builder()
                    .message("SportType not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        sportTypeRepository.deleteById(id);
        return ApiResponse.builder()
                .message("SportType deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    public List<ResSportType> getAllSportType() {
        List<Sports_type> sportsType1 = sportTypeRepository.findAll();
        List<ResSportType> resSportType = new ArrayList<>();
        for (Sports_type sportsType : sportsType1) {
            ResSportType resSportType1 = ResSportType.builder()
                    .name(sportsType.getName())
                    .category(sportsType.getCategory())
                    .description(sportsType.getDescription())
                    .minAge(sportsType.getMinAge())
                    .maxAge(sportsType.getMaxAge())
                    .build();

            resSportType.add(resSportType1);
        }
        return resSportType;
    }

    public ResSportType getOneSortType(Long id) {
        Optional<Sports_type> byId = sportTypeRepository.findById(id);
        if (byId.isPresent()) {
            Sports_type sportsType = byId.get();
            ResSportType resSportType= ResSportType.builder()
                    .name(sportsType.getName())
                    .category(sportsType.getCategory())
                    .description(sportsType.getDescription())
                    .minAge(sportsType.getMinAge())
                    .maxAge(sportsType.getMaxAge())
                    .build();
            return resSportType;
        }
        return null;
    }

}