package com.marian.tennis.api.tarifs.entity;

import com.marian.tennis.api.tarifs.utils.validators.ValidAfterDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@ValidAfterDate
@Table(name = "tarifs", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "name")})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifsEntity {

    public static final String DATE_FORMAT="dd-MM-yy";

    @Id
    @GeneratedValue()
    private Long idTarif;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "prix")
    private float prix;
    @Column(name = "start_date")
    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "start_time")
    @NotNull
    private LocalTime startTime;
    @NotNull
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "weekend", nullable = false)
    private boolean weekend;
    @Column(name = "actif", nullable = false)
    private boolean actif;
    @Column(name = "special_tarif", nullable = false)
    private Boolean specialTarif;
    @Column(name = "default_tarif", nullable = false)
    private Boolean defaultTarif;

//    @ElementCollection
//    private List<String> terrainsIds;

}
