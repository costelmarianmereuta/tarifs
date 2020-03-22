package com.marian.tennis.api.tarifs.controller;

import com.marian.tennis.api.tarifs.assemblers.TarifResourceAssembler;
import com.marian.tennis.api.tarifs.entity.TarifsEntity;
import com.marian.tennis.api.tarifs.model.RequestBodyTarif;
import com.marian.tennis.api.tarifs.model.TarifResource;
import com.marian.tennis.api.tarifs.service.TarifService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

        return ResponseEntity.ok(tarifService.createTarif(requestBodyTarif));
    }

    @GetMapping
    public ResponseEntity<List<TarifResource>> getTarifs() {

        List<TarifsEntity> tarifs = tarifService.getTarifs();
        return ResponseEntity.ok(new ArrayList<>(tarifResourceAssembler.toCollectionModel(tarifs).getContent()));
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<TarifResource> getTarif(@PathVariable String name) throws NotFoundException {
        TarifsEntity entity = tarifService.getTarif(name);
        return ResponseEntity.ok(tarifResourceAssembler.toModel(entity));
    }
}
