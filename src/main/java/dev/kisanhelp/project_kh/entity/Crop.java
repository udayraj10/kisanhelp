package dev.kisanhelp.project_kh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "crops")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String cropName;

    @Column(name = "category")
    private String category;

    @Column(name = "weeks_to_harvest")
    private Integer weeksToHarvest;

    @Column(name = "min_temp_celsius")
    private BigDecimal minTempCelsius;

    @Column(name = "max_temp_celsius")
    private BigDecimal maxTempCelsius;

    @Column(name = "water_need_mm")
    private Integer waterNeedMM;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FertilizerPlan> fertilizerPlans;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WaterSchedule> waterSchedules;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SoilSuitability> soilSuitabilities;

    @ManyToMany
    @JoinTable(name = "crop_seasons", joinColumns = @JoinColumn(name = "crop_id"), inverseJoinColumns = @JoinColumn(name = "season_id"))
    private List<Season> seasons = new ArrayList<>();
}
