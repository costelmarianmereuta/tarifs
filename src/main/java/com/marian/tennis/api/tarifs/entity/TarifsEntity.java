package com.marian.tennis.api.tarifs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tarifs")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifsEntity {

    public static final String DATE_FORMAT="dd-MM-yy";

    @Id
    @GeneratedValue
    private Integer idTarif;
    @NotNull
    @Column(name = "name",unique = true)
    private String name;
    @NotNull
    @Column(name = "prix")
    private double prix;
    @Column(name = "start_date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
//    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "start_time")
    @NotNull
    private LocalTime startTime;
    @NotNull
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "weekend")
    @AssertFalse
    private boolean weekend;
    @AssertFalse
    @Column(name = "actif")
    private boolean actif;
    @Column(name = "special_tarif")
    @AssertFalse
    private Boolean specialTarif;
    @AssertFalse
    @Column(name = "default_tarif")
    private Boolean defaultTarif;

    @ElementCollection
    private List<String> terrainsIds;

}
