package com.marian.tennis.api.tarifs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Error
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Error implements Serializable {
  private static final long serialVersionUID = 1L;

  


  @JsonProperty("uuid")
  private String uuid;

  


  @JsonProperty("exceptionType")
  private String exceptionType;

  


  @JsonProperty("message")
  private String message;

}
