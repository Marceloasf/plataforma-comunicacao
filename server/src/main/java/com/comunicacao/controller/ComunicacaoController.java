package com.comunicacao.controller;

import com.comunicacao.domain.Comunicacao;
import com.comunicacao.service.ComunicacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comunicacoes")
public class ComunicacaoController {

    private final ComunicacaoService service;

    public ComunicacaoController(ComunicacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Comunicacao>> findAll() {

        return ResponseEntity.ok(this.service.findAll());
    }
}
