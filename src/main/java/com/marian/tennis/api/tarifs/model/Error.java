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
 * Error
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Error extends RepresentationModel<Error> implements Serializable {
  private static final long serialVersionUID = 1L;


  @JsonProperty("uuid")
  private String uuid;


  @JsonProperty("exceptionType")
  private String exceptionType;


  @JsonProperty("message")
  private String message;

}
