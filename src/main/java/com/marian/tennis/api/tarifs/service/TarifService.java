package com.marian.tennis.api.tarifs.service;

import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import com.marian.tennis.api.tarifs.model.RequestBodyTarif;
import com.marian.tennis.api.tarifs.repositories.TarifRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.marian.tennis.api.tarifs.utils.Constants.TARIF_NOT_FOUND;


@Service
public class TarifService {

    private TarifRepository tarifsRepository;

    public TarifService(TarifRepository tarifsRepository) {
        this.tarifsRepository = tarifsRepository;
    }

    public TarifsEntity createTarif(RequestBodyTarif requestBodyTarif) {

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

    public List<TarifsEntity> getTarifs() {
        return tarifsRepository.findAll();
    }

    public TarifsEntity getTarif(String name) {
        TarifsEntity byName = tarifsRepository.findByName(name);
        if (!ObjectUtils.isEmpty(byName)) {
            return byName;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, TARIF_NOT_FOUND);
        }


    }
}
