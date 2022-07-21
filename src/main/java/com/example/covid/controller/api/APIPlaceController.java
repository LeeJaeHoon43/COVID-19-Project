package com.example.covid.controller.api;

import com.example.covid.constant.PlaceType;
import com.example.covid.dto.ApiDataResponse;
import com.example.covid.dto.PlaceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public ApiDataResponse<List<PlaceDTO>> getPlaces(){
        return ApiDataResponse.of(List.of(PlaceDTO.of(
                PlaceType.COMMON, "랄라배드민턴장", "서울시 강남구 강남대로 1234", "010-1234-5678", 30, "신장개업")
        ));
    }

    @PostMapping("/places")
    public Boolean createPlace(){
        return true;
    }

    @GetMapping("/places/{placeId}")
    public ApiDataResponse<PlaceDTO> getPlace(@PathVariable Integer placeId){
        if (placeId.equals(2)){
            return ApiDataResponse.of(null);
        }

        return ApiDataResponse.of(PlaceDTO.of(PlaceType.COMMON, "랄라배드민턴장", "서울시 강남구 강남대로 1234", "010-1234-5678", 30, "신장개업"));
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId){
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(@PathVariable Integer placeId){
        return true;
    }
}
