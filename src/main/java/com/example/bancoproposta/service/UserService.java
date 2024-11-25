//package com.example.projeto_tarefa.service;
//
//import com.example.projeto_tarefa.repositories.ProjetoRepository;
//import com.example.projeto_tarefa.repositories.TarefaRepository;
//import com.example.projeto_tarefa.model.Projeto;
//import com.example.projeto_tarefa.model.Tarefa;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private ProjetoRepository projetoRepository;
//
//    @Autowired
//    private TarefaRepository tarefaRepository;
//
//    // CRUD Projeto
//    public List<Projeto> getAllProjetos() {
//        return projetoRepository.findAll();
//    }
//
//    public Optional<Projeto> getProjetoById(Long id) {
//        return projetoRepository.findById(id);
//    }
//
//    public Projeto createProjeto(Projeto projeto) {
//        return projetoRepository.save(projeto);
//    }
//
//    public void deleteProjeto(Long id) {
//        projetoRepository.deleteById(id);
//    }
//
//    // CRUD Tarefa
//    public List<Tarefa> getAllTarefas() {
//        return tarefaRepository.findAll();
//    }
//
//    public Optional<Tarefa> getTarefaById(Long id) {
//        return tarefaRepository.findById(id);
//    }
//
//    public Tarefa createTarefa(Tarefa tarefa) {
//        return tarefaRepository.save(tarefa);
//    }
//
//    public void deleteTarefa(Long id) {
//        tarefaRepository.deleteById(id);
//    }
//}
//
//
//
////package com.example.projeto_tarefa.service;
////
////import com.example.projeto_tarefa.model.Projeto;
////import com.example.projeto_tarefa.model.Tarefa;
////import org.springframework.stereotype.Service;
////
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Optional;
////
////@Service
////public class UserService {
////    private List<Projeto> projetos = new ArrayList<>();
////    private List<Tarefa> tarefas = new ArrayList<>();
////
////    // CRUD Projeto
////    public List<Projeto> getAllProjetos() {
////        return projetos;
////    }
////
////    public Optional<Projeto> getProjetoById(Long id) {
////        return projetos.stream().filter(p -> p.getId().equals(id)).findFirst();
////    }
////
////    public Projeto createProjeto(Projeto projeto) {
////        projetos.add(projeto);
////        return projeto;
////    }
////
////    public void deleteProjeto(Long id) {
////        projetos.removeIf(p -> p.getId().equals(id));
////    }
////
////    // CRUD Tarefa
////    public List<Tarefa> getAllTarefas() {
////        return tarefas;
////    }
////
////    public Optional<Tarefa> getTarefaById(Long id) {
////        return tarefas.stream().filter(t -> t.getId().equals(id)).findFirst();
////    }
////
////    public Tarefa createTarefa(Tarefa tarefa) {
////        tarefas.add(tarefa);
////        return tarefa;
////    }
////
////    public void deleteTarefa(Long id) {
////        tarefas.removeIf(t -> t.getId().equals(id));
////    }
////}
