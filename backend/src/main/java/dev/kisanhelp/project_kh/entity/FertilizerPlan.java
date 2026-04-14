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
@Table(name = "fertilizer_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FertilizerPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @Column(name = "stage")
    private String stage;

    @Column(name = "fertilizer_name")
    private String fertilizerName;

    @Column(name = "quantity_per_acre")
    private BigDecimal quantityPerAcre;

    @Column(name = "unit")
    private String unit;

    @Column(name = "timing_weeks")
    private Integer timingWeeks;

    @Column(name = "instructions")
    private String instructions;
}
