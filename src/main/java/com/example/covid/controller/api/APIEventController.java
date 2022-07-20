package com.example.covid.controller.api;

import com.example.covid.exception.GeneralException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/events")
    public List<String> getEvents() {
        // throw new HttpRequestMethodNotSupportedException("스프링 에러 테스트");
        return List.of("event1", "event2");
    }

    @PostMapping("/events")
    public Boolean createEvent() {
        // throw new GeneralException("GeneralException 테스트 메시지");
        return true;
    }

    @GetMapping("/events/{eventId}")
    public String getEvent(@PathVariable Integer eventId){
        return "event " + eventId;
    }

    @PutMapping("/events/{eventId}")
    public Boolean modifyEvent(@PathVariable Integer eventId){
        return true;
    }

    @DeleteMapping("/events/{eventId}")
    public Boolean removeEvent(@PathVariable Integer eventId){
        return true;
    }
}
