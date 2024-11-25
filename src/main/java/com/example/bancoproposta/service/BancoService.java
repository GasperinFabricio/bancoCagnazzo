package com.example.bancoproposta.service;

import com.example.bancoproposta.repositories.BancoRepository;
import com.example.bancoproposta.model.Banco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    // CRUD Banco
    public List<Banco> getAllBancos() {
        return bancoRepository.findAll();
    }

    public Optional<Banco> getBancoById(Long id) {
        return bancoRepository.findById(id);
    }

    public Banco createBanco(Banco banco) {
        return bancoRepository.save(banco);
    }

    public void deleteBanco(Long id) {
        bancoRepository.deleteById(id);
    }
}
