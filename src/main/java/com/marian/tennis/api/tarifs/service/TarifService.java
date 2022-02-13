package com.marian.tennis.api.tarifs.service;

import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import com.marian.tennis.api.tarifs.model.RequestBodyTarif;
import com.marian.tennis.api.tarifs.repositories.TarifRepository;
import com.marian.tennis.api.tarifs.utils.Constants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static com.marian.tennis.api.tarifs.utils.Constants.TARIF_NOT_FOUND;


@Service
public class TarifService {

    private TarifRepository tarifsRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public TarifService(TarifRepository tarifsRepository) {
        this.tarifsRepository = tarifsRepository;
    }

    public TarifsEntity createTarif(RequestBodyTarif requestBodyTarif) {


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
                .build();

        try {
            tarifsRepository.save(tarifsEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getCause());
        }


        return tarifsEntity;
    }

    public List<TarifsEntity> getTarifs(List<String> names) {
        if (!CollectionUtils.isEmpty(names)) {
            return tarifsRepository.findAllByNameIn(names);
        } else {
            return tarifsRepository.findAll();
        }
    }

    public TarifsEntity updateTarif(RequestBodyTarif requestBodyTarif, Long id) {
        TarifsEntity tarif = tarifsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, TARIF_NOT_FOUND));
        if (!ObjectUtils.isEmpty(tarif)) {
            tarif.setActif(requestBodyTarif.getActif());
            tarif.setDefaultTarif(requestBodyTarif.getDefaultTarif());
            tarif.setStartDate(requestBodyTarif.getStartDate());
            tarif.setEndDate(requestBodyTarif.getEndDate());
            tarif.setStartTime(LocalTime.parse(requestBodyTarif.getStartTime(), formatter));
            tarif.setEndTime(LocalTime.parse(requestBodyTarif.getEndTime(), formatter));
            tarif.setName(requestBodyTarif.getName());
            tarif.setPrix(requestBodyTarif.getPrix());
            tarif.setSpecialTarif(requestBodyTarif.getSpecialTarif());
            tarif.setWeekend(requestBodyTarif.getWeekend());
            //check how update list
            try {
                tarifsRepository.save(tarif);
                return tarif;
            } catch (Exception e) {
                Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e.getCause().getCause()).getConstraintViolations();
                ConstraintViolation<?> constraintViolation = constraintViolations.stream().findFirst().orElse(null);

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message: " + constraintViolation.getMessage() + " on the value: " + constraintViolation.getInvalidValue());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, TARIF_NOT_FOUND);
        }
    }

    public TarifsEntity getTarif(String name) {
        TarifsEntity byName = tarifsRepository.findByName(name);
        if (!ObjectUtils.isEmpty(byName)) {
            return byName;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, TARIF_NOT_FOUND);
        }
    }

    @Transactional
    public String removeTarif(String name) {
        if (!ObjectUtils.isEmpty(tarifsRepository.findByName(name))) {
            tarifsRepository.deleteByName(name);

            //make logic to delete tarif from terrain without kafka

            return Constants.TARIF_REMOVED + name;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, TARIF_NOT_FOUND);
        }

    }
}
