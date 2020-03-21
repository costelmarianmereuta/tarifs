package com.marian.tennis.api.tarifs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.marian.tennis.api.tarifs.model.Terrain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

  


  @JsonProperty("name")
  private String name;

  


  @JsonProperty("prix")
  private Float prix;

  
  @Valid


  @JsonProperty("startDate")
  private LocalDate startDate;

  
  @Valid


  @JsonProperty("endDate")
  private LocalDate endDate;

  
@Pattern(regexp="(?:[01]\\d|2[0123]):(?:[012345]\\d)") 

  @JsonProperty("startTime")
  private String startTime;

  
@Pattern(regexp="(?:[01]\\d|2[0123]):(?:[012345]\\d)") 

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

  
  @Valid


  @JsonProperty("terrains")
  private List<Terrain> terrains;


	public TarifResource addTerrainsItem(Terrain terrainsItem) {
		if (this.terrains == null) {
		this.terrains = new ArrayList<>();
		}
	this.terrains.add(terrainsItem);
	return this;
	}
}
