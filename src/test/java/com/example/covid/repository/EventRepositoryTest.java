package com.example.covid.repository;

import com.example.covid.constant.EventStatus;
import com.example.covid.dto.EventViewResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DB - 이벤트")
@DataJpaTest
class EventRepositoryTest {

    private final EventRepository eventRepository;

    EventRepositoryTest(@Autowired EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @DisplayName("이벤트 뷰 데이터를 검색 파라미터와 함께 조회하면, 조건에 맞는 데이터를 페이징 처리하여 리턴한다.")
    @Test
    void givenSearchParams_whenFindingEventViewResponse_thenReturnsEventViewResponsePage(){

        Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
                "축구장",
                "운동1",
                EventStatus.OPENED,
                LocalDateTime.of(2022, 8,1,0,0,0),
                LocalDateTime.of(2022, 8,1,23,0,0),
                PageRequest.of(0, 5)
        );

        assertThat(eventPage.getTotalPages()).isEqualTo(1);
        assertThat(eventPage.getNumberOfElements()).isEqualTo(1);
        assertThat(eventPage.getContent().get(0))
                .hasFieldOrPropertyWithValue("placeName", "축구장")
                .hasFieldOrPropertyWithValue("eventName", "운동1")
                .hasFieldOrPropertyWithValue("eventStatus", EventStatus.OPENED)
                .hasFieldOrPropertyWithValue("eventStartDatetime", LocalDateTime.of(2022, 8,1,9,0,0))
                .hasFieldOrPropertyWithValue("eventEndDatetime", LocalDateTime.of(2022, 8,1,12,0,0));
    }

    @DisplayName("이벤트 뷰 데이터 검색어에 따른 조회 결과가 없으면, 빈 데이터를 페이징 정보와 함께 리턴한다.")
    @Test
    void givenSearchParams_whenFindingNonexistentEventViewPage_thenReturnsEmptyEventViewResponsePage() {

        Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
                "없는 장소",
                "없는 이벤트",
                null,
                LocalDateTime.of(1000, 1, 1, 1, 1, 1),
                LocalDateTime.of(1000, 1, 1, 1, 1, 0),
                PageRequest.of(0, 5)
        );
        
        assertThat(eventPage).hasSize(0);
    }

    @DisplayName("이벤트 뷰 데이터를 검색 파라미터 없이 페이징 값만 주고 조회하면, 전체 데이터를 페이징 처리하여 리턴한다.")
    @Test
    void givenPagingInfoOnly_whenFindingEventViewPage_thenReturnsEventViewResponsePage() {

        Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
                null,
                null,
                null,
                null,
                null,
                PageRequest.of(0, 5)
        );


        assertThat(eventPage).hasSize(5);
    }

    @DisplayName("이벤트 뷰 데이터를 페이징 정보 없이 조회하면, 에러를 리턴한다.")
    @Test
    void givenNothing_whenFindingEventViewPage_thenThrowsError() {

        Throwable t = Assertions.catchThrowable(() -> eventRepository.findEventViewPageBySearchParams(
                null,
                null,
                null,
                null,
                null,
                null
        ));

        assertThat(t).isInstanceOf(InvalidDataAccessApiUsageException.class);
    }
}