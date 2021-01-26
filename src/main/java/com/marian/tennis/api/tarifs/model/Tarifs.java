package com.marian.tennis.api.tarifs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Tarifs
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tarifs extends RepresentationModel<Tarifs> implements Serializable {
    private static final long serialVersionUID = 1L;


    @Valid


    @JsonProperty("tarifList")
    private List<TarifResource> tarifList;


    public Tarifs addTarifListItem(TarifResource tarifListItem) {
        if (this.tarifList == null) {
            this.tarifList = new ArrayList<>();
        }
        this.tarifList.add(tarifListItem);
        return this;
    }
}
