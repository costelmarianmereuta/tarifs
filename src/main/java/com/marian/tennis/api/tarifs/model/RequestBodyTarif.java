package com.marian.tennis.api.tarifs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * RequestBodyTarif
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestBodyTarif implements Serializable {
  private static final long serialVersionUID = 1L;

    @NotNull



  @JsonProperty("name")
  private String name;

    @NotNull



  @JsonProperty("prix")
  private Float prix;

    @NotNull

  @Valid


  @JsonProperty("startDate")
  private LocalDate startDate;

    @NotNull

  @Valid


  @JsonProperty("endDate")
  private LocalDate endDate;

    @NotNull

@Pattern(regexp="(?:[01]\\d|2[0123]):(?:[012345]\\d)") 

  @JsonProperty("startTime")
  private String startTime;

    @NotNull

@Pattern(regexp="(?:[01]\\d|2[0123]):(?:[012345]\\d)") 

  @JsonProperty("endTime")
  private String endTime;

    @NotNull



  @JsonProperty("weekend")
  private Boolean weekend = false;

    @NotNull



  @JsonProperty("actif")
  private Boolean actif = false;

    @NotNull



  @JsonProperty("specialTarif")
  private Boolean specialTarif = false;

    @NotNull



  @JsonProperty("defaultTarif")
  private Boolean defaultTarif = false;

  


  @JsonProperty("nameTerrain")
  private String nameTerrain;

}
