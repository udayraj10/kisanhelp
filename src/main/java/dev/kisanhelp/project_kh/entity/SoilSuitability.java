package dev.kisanhelp.project_kh.entity;

import dev.kisanhelp.project_kh.util.SoilType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "soil_crop_suitability")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoilSuitability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @Enumerated(EnumType.STRING)
    @Column(name = "soil_type")
    private SoilType soilType;

    @Column(name = "is_suitable")
    private Boolean isSuitable;

    @Column(name = "reason")
    private String reason;
}