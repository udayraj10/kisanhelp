package dev.kisanhelp.project_kh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.kisanhelp.project_kh.entity.CalendarMonth;
import dev.kisanhelp.project_kh.entity.Season;
import dev.kisanhelp.project_kh.repository.MonthRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonthService {

    private final MonthRepository monthRepository;

    public List<CalendarMonth> getMonths(List<Season> seasons) {
        return monthRepository.findBySeasonIn(seasons);
    }
}
