package com.example.covid.controller.api;

import com.example.covid.constant.PlaceType;
import com.example.covid.dto.ApiDataResponse;
import com.example.covid.dto.PlaceRequest;
import com.example.covid.dto.PlaceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RequestMapping("/api")
// @RestController
public class APIPlaceController {

    @GetMapping("/places")
    public ApiDataResponse<List<PlaceResponse>> getPlaces() {
        return ApiDataResponse.of(List.of(PlaceResponse.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        )));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/places")
    public ApiDataResponse<Void> createPlace(@RequestBody PlaceRequest placeRequest) {
        return ApiDataResponse.empty();
    }

    @GetMapping("/places/{placeId}")
    public ApiDataResponse<PlaceResponse> getPlace(@PathVariable Long placeId) {
        if (placeId.equals(2L)) {
            return ApiDataResponse.empty();
        }

        return ApiDataResponse.of(PlaceResponse.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        ));
    }

    @PutMapping("/places/{placeId}")
    public ApiDataResponse<Void> modifyPlace(
            @PathVariable Long placeId,
            @RequestBody PlaceRequest placeRequest
    ) {
        return ApiDataResponse.empty();
    }

    @DeleteMapping("/places/{placeId}")
    public ApiDataResponse<Void> removePlace(@PathVariable Long placeId) {
        return ApiDataResponse.empty();
    }
}
