package org.example.online_sports.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.request.ReqSportType;
import org.example.online_sports.response.ResSportType;
import org.example.online_sports.service.SportTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/sportType")
public class SportTypeController {
    private final SportTypeService sportTypeService;

    @PostMapping("/save")
    public ApiResponse saveSportType(@RequestBody ReqSportType reqSportType) {
        return sportTypeService.saveSportType(reqSportType);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteSportType(@PathVariable Long id) {
        return sportTypeService.deleteSportType(id);
    }

    @GetMapping("/list")
    public List<ResSportType> getAllSportType() {
        return sportTypeService.getAllSportType();
    }

    @GetMapping("/{id}")
    public ResSportType getOneSportType(@PathVariable Long id) {
        return sportTypeService.getOneSortType(id);
    }

    @PutMapping("/{id}")
    public ApiResponse updateSportType(@PathVariable Long id, @RequestBody ReqSportType reqSportType) {
        return sportTypeService.updateSportType(id, reqSportType);
    }
}
