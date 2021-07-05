package com.comunicacao.controller;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.enums.StatusComunicacaoEnum;
import com.comunicacao.service.ComunicacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comunicacoes")
public class ComunicacaoController {

    private final ComunicacaoService service;

    public ComunicacaoController(ComunicacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> saveComunicacao(@RequestBody Comunicacao novaComunicacao) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.saveComunicacao(novaComunicacao));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<StatusComunicacaoEnum> findStatusByComunicacaoId(@PathVariable("id") Long id) {

        return ResponseEntity.ok(this.service.findStatusByComunicacaoId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {

        this.service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
