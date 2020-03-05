package com.marian.tennis.api.tarifs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.openapitools.jackson.nullable.JsonNullable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TarifResource
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarifResource implements Serializable {
  private static final long serialVersionUID = 1L;

  


  @JsonProperty("id")
  private Long id;

  


  @JsonProperty("name")
  private String name;

  
  @Valid


  @JsonProperty("prix")
  private BigDecimal prix;

  
  @Valid


  @JsonProperty("startDate")
  private LocalDate startDate;

  
  @Valid


  @JsonProperty("endDate")
  private LocalDate endDate;

  


  @JsonProperty("startTime")
  private String startTime;

  


  @JsonProperty("endTime")
  private String endTime;

  


  @JsonProperty("weekend")
  private Boolean weekend;

  


  @JsonProperty("actif")
  private Boolean actif;

  


  @JsonProperty("specialTarif")
  private Boolean specialTarif;

  


  @JsonProperty("defaultTarif")
  private Boolean defaultTarif;

}
