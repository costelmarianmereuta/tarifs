package com.marian.tennis.api.tarifs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
/**
 * Terrain
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Terrain extends RepresentationModel<Terrain> implements Serializable {
  private static final long serialVersionUID = 1L;


  @JsonProperty("name")
  private String name;

}
