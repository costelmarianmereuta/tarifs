package com.marian.tennis.api.tarifs.controller;

import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import com.marian.tennis.api.tarifs.model.RequestBodyTarif;
import com.marian.tennis.api.tarifs.service.TarifService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TarifController {
    private TarifService tarifService;

    public TarifController(TarifService tarifService) {
        this.tarifService = tarifService;
    }

    @PostMapping("/tarifs")
    public ResponseEntity<TarifsEntity> createTarif(@RequestBody @Valid RequestBodyTarif requestBodyTarif){

        return ResponseEntity.ok(tarifService.createTarif(requestBodyTarif));
    }
}
