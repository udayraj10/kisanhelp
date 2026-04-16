package dev.kisanhelp.project_kh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.kisanhelp.project_kh.entity.CalendarMonth;
import dev.kisanhelp.project_kh.entity.Season;

@Repository
public interface MonthRepository extends JpaRepository<CalendarMonth, Integer> {

    @Query("SELECT m FROM CalendarMonth m WHERE m.season IN :seasons ORDER BY m.season.id, m.id ASC")
    List<CalendarMonth> findBySeasonIn(@Param("seasons") List<Season> seasons);
}
