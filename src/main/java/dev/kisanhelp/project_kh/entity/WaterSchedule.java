package dev.kisanhelp.project_kh.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "water_schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @Column(name = "stage")
    private String stage;

    @Column(name = "frequency_days")
    private Integer frequencyDays;

    @Column(name = "quantity_mm")
    private BigDecimal quantityMM;

    @Column(name = "week_start")
    private Integer weekStart;

    @Column(name = "week_end")
    private Integer weekEnd;

    @Column(name = "notes")
    private String notes;
}
