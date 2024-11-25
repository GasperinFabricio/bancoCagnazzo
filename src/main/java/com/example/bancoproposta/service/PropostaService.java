package com.example.bancoproposta.service;

import com.example.bancoproposta.repositories.PropostaRepository;
import com.example.bancoproposta.model.Proposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    // CRUD Proposta
    public List<Proposta> getAllPropostas() {
        return propostaRepository.findAll();
    }

    public Optional<Proposta> getPropostaById(Long id) {
        return propostaRepository.findById(id);
    }

    public Proposta createProposta(Proposta proposta) {
        return propostaRepository.save(proposta);
    }

    public void deleteProposta(Long id) {
        propostaRepository.deleteById(id);
    }
}
