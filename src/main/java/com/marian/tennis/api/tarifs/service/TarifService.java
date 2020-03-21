package com.marian.tennis.api.tarifs.service;

import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import com.marian.tennis.api.tarifs.model.RequestBodyTarif;
import com.marian.tennis.api.tarifs.model.TarifResource;
import com.marian.tennis.api.tarifs.repositories.TarifRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Service
public class TarifService {

    private TarifRepository tarifsRepository;
    TarifResource tarifResource;

    public TarifService(TarifRepository tarifsRepository) {
        this.tarifsRepository = tarifsRepository;
    }

    public TarifsEntity createTarif(RequestBodyTarif requestBodyTarif){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        TarifsEntity tarifsEntity = TarifsEntity.builder()
                .name(requestBodyTarif.getName())
                .prix(requestBodyTarif.getPrix())
                .startDate(requestBodyTarif.getStartDate())
                .endDate(requestBodyTarif.getEndDate())
                .startTime(LocalTime.parse(requestBodyTarif.getStartTime(), formatter))
                .endTime(LocalTime.parse(requestBodyTarif.getEndTime(), formatter))
                .weekend(requestBodyTarif.getWeekend())
                .actif(requestBodyTarif.getActif())
                .specialTarif(requestBodyTarif.getSpecialTarif())
                .defaultTarif(requestBodyTarif.getDefaultTarif())
                //todo implement when terrains are ready add terrains with check
                .build();

        tarifsRepository.save(tarifsEntity);

        return tarifsEntity;
    }
}
