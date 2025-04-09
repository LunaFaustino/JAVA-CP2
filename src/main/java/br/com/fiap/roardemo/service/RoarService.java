package br.com.fiap.roardemo.service;

import br.com.fiap.roardemo.model.Roar;
import br.com.fiap.roardemo.repository.RoarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoarService {

    private final RoarRepository roarRepository;

    @Autowired
    public RoarService(RoarRepository roarRepository) {
        this.roarRepository = roarRepository;
    }

    public List<Roar> getAllRoars() {
        return roarRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Roar> getRoarsByUserId(String userId) {
        return roarRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Transactional
    public Roar addRoar(String content, String username, String userId) {
        Roar roar = new Roar(content, username, userId);
        return roarRepository.save(roar);
    }

    @Transactional
    public Roar likeRoar(Long roarId) {
        return roarRepository.findById(roarId)
                .map(roar -> {
                    roar.setLikes(roar.getLikes() + 1);
                    return roarRepository.save(roar);
                })
                .orElseThrow(() -> new RuntimeException("Roar não encontrado com ID: " + roarId));
    }

    public Roar getRoarById(Long roarId) {
        return roarRepository.findById(roarId)
                .orElseThrow(() -> new RuntimeException("Roar não encontrado com ID: " + roarId));
    }
}