package com.example.covid.service;

import com.example.covid.constant.ErrorCode;
import com.example.covid.constant.EventStatus;
import com.example.covid.dto.EventDto;
import com.example.covid.exception.GeneralException;
import com.example.covid.repository.EventRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDto> getEvents(Predicate predicate) {
        try {
            return StreamSupport.stream(eventRepository.findAll(predicate).spliterator(), false)
                    .map(EventDto::of)
                    .toList();
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public List<EventDto> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        try {
            return null;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public Optional<EventDto> getEvent(Long eventId) {
        try {
            return eventRepository.findById(eventId).map(EventDto::of);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean createEvent(EventDto eventDTO) {
        try {
            if (eventDTO == null) {
                return false;
            }

            eventRepository.save(eventDTO.toEntity());
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean modifyEvent(Long eventId, EventDto dto) {
        try {
            if (eventId == null || dto == null) {
                return false;
            }

            eventRepository.findById(eventId).ifPresent(event -> eventRepository.save(dto.updateEntity(event)));
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean removeEvent(Long eventId) {
        try {
            if (eventId == null) {
                return false;
            }

            eventRepository.deleteById(eventId);
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }
}
