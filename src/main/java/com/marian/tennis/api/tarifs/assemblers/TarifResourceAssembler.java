package com.marian.tennis.api.tarifs.assemblers;

import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import com.marian.tennis.api.tarifs.model.TarifResource;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TarifResourceAssembler implements RepresentationModelAssembler<TarifsEntity, TarifResource> {


    @Override
    public TarifResource toModel(TarifsEntity entity) {
        return TarifResource.builder()
                .name(entity.getName())
                .prix(entity.getPrix())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .startTime(entity.getStartTime().toString())
                .endTime(entity.getEndTime().toString())
                .actif(entity.isActif())
                .defaultTarif(entity.getDefaultTarif())
                .specialTarif(entity.getSpecialTarif())
                .weekend(entity.isWeekend())
                .build();
    }


}
