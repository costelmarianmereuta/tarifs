package com.marian.tennis.api.tarifs.controller;

import com.marian.tennis.api.tarifs.assemblers.TarifResourceAssembler;
import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import com.marian.tennis.api.tarifs.model.RequestBodyTarif;
import com.marian.tennis.api.tarifs.model.TarifResource;
import com.marian.tennis.api.tarifs.model.Tarifs;
import com.marian.tennis.api.tarifs.service.TarifService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/tarifs")
public class TarifController {
    private TarifService tarifService;
    private TarifResourceAssembler tarifResourceAssembler;

    public TarifController(TarifService tarifService, TarifResourceAssembler tarifResourceAssembler) {
        this.tarifService = tarifService;
        this.tarifResourceAssembler = tarifResourceAssembler;
    }

    @PostMapping
    public ResponseEntity<TarifsEntity> createTarif(@RequestBody @Valid RequestBodyTarif requestBodyTarif){


        return ResponseEntity.status(201).body(tarifService.createTarif(requestBodyTarif));
    }

    @GetMapping
    public ResponseEntity<Tarifs> getTarifs(@RequestParam(required = false) List<String> names) {
        List<TarifsEntity> tarifsEntities = tarifService.getTarifs(names);
        Collection<TarifResource> content = tarifResourceAssembler.toCollectionModel(tarifsEntities).getContent();
        return ResponseEntity.ok().body(Tarifs.builder().tarifList(new ArrayList<>(content)).build());
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<TarifResource> getTarif(@PathVariable String name) {
        TarifsEntity entity = tarifService.getTarif(name);
        return ResponseEntity.ok(tarifResourceAssembler.toModel(entity));
    }

    @PutMapping
    public ResponseEntity<TarifResource> updateTarif(@RequestBody @Valid RequestBodyTarif requestBodyTarif) {
        TarifsEntity tarifsEntity = tarifService.updateTarif(requestBodyTarif);
        return ResponseEntity.ok(tarifResourceAssembler.toModel(tarifsEntity));
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<String> deleteTarif(@PathVariable String name) {

        return ResponseEntity.ok().body(tarifService.removeTarif(name));
    }

}
