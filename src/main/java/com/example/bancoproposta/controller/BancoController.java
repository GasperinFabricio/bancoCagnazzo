package com.example.bancoproposta.controller;

import com.example.bancoproposta.model.Banco;
import com.example.bancoproposta.model.Proposta;
import com.example.bancoproposta.service.BancoService;
import com.example.bancoproposta.service.PropostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/banco")
public class BancoController {

    private final BancoService bancoService;
    private final PropostaService propostaService;

    @Autowired
    public BancoController(final BancoService bancoService, final PropostaService propostaService) {
        this.bancoService = bancoService;
        this.propostaService = propostaService;
    }

    @Tag(name = "GET")
    @Operation(summary = "Get all banks", description = "Retrieve all banks")
    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Banco> getAllBancos() {
        return bancoService.getAllBancos();
    }

    @Tag(name = "GET")
    @Operation(summary = "Get bank by ID", description = "Retrieve a bank by its ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Banco> getBancoById(@PathVariable Long id) {
        Optional<Banco> banco = bancoService.getBancoById(id);
        return banco.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Tag(name = "POST")
    @Operation(summary = "Create a bank", description = "Create a new bank")
    @PostMapping(consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> createBanco(@RequestBody Banco banco) {
        try {
            banco.getPropostas().forEach(proposta -> proposta.setBanco(banco));
            bancoService.createBanco(banco);
            return ResponseEntity.status(HttpStatus.CREATED).body("Banco criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na criação do banco: " + e.getMessage());
        }
    }

    @Tag(name = "DELETE")
    @Operation(summary = "Delete a bank", description = "Delete a bank by its ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteBanco(@PathVariable Long id) {
        Optional<Banco> banco = bancoService.getBancoById(id);
        if (banco.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado");
        }
        bancoService.deleteBanco(id);
        return ResponseEntity.status(HttpStatus.OK).body("Banco deletado com sucesso");
    }

    @Tag(name = "PUT")
    @Operation(summary = "Update a bank", description = "Update an existing bank")
    @PutMapping(consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> editBanco(@RequestBody Banco banco) {
        try {
            bancoService.createBanco(banco);
            return ResponseEntity.status(HttpStatus.OK).body("Banco atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na atualização do banco: " + e.getMessage());
        }
    }

    @Tag(name = "POST")
    @Operation(summary = "Add a proposal to the bank", description = "Add a proposal to the bank")
    @PostMapping("/{bancoId}/proposta")
    public ResponseEntity<String> addPropostaToBanco(@PathVariable Long bancoId, @RequestBody Proposta proposta) {
        Optional<Banco> banco = bancoService.getBancoById(bancoId);
        if (banco.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado");
        }
        Banco banc = banco.get();
        proposta.setBanco(banc);  // Associa a proposta ao banco
        propostaService.createProposta(proposta);  // Salva a proposta
        banc.addProposta(proposta);  // Adiciona a proposta ao banco
        bancoService.createBanco(banc);  // Salva o banco
        return ResponseEntity.status(HttpStatus.CREATED).body("Proposta adicionada ao banco com sucesso");
    }

    @Tag(name = "PUT")
    @Operation(summary = "Update a proposal in the bank", description = "Update a proposal in the bank")
    @PutMapping("/{bancoId}/proposta/{propostaId}")
    public ResponseEntity<String> updatePropostaForBanco(@PathVariable Long bancoId, @PathVariable Long propostaId, @RequestBody Proposta propostaAtualizada) {
        Optional<Banco> banco = bancoService.getBancoById(bancoId);
        if (banco.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado");
        }

        Optional<Proposta> proposta = propostaService.getPropostaById(propostaId);
        if (proposta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada");
        }

        propostaAtualizada.setId(propostaId);
        propostaAtualizada.setBanco(banco.get());
        propostaService.createProposta(propostaAtualizada);
        return ResponseEntity.status(HttpStatus.OK).body("Proposta atualizada com sucesso");
    }

    @Tag(name = "DELETE")
    @Operation(summary = "Delete a proposal from the bank", description = "Delete a proposal from the bank")
    @DeleteMapping("/{bancoId}/proposta/{propostaId}")
    public ResponseEntity<String> removePropostaFromBanco(@PathVariable Long bancoId, @PathVariable Long propostaId) {
        Optional<Banco> banco = bancoService.getBancoById(bancoId);
        Optional<Proposta> proposta = propostaService.getPropostaById(propostaId);

        if (banco.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado");
        }
        if (proposta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada");
        }

        banco.get().removeProposta(proposta.get());
        bancoService.createBanco(banco.get());
        return ResponseEntity.status(HttpStatus.OK).body("Proposta removida do banco com sucesso");
    }
}
