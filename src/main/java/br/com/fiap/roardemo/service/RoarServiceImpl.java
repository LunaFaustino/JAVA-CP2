package br.com.fiap.roardemo.service;

import br.com.fiap.roardemo.model.Roar;
import br.com.fiap.roardemo.repository.RoarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoarServiceImpl implements RoarService{

    private final RoarRepository roarRepository;

    public RoarServiceImpl(RoarRepository roarRepository) {
        this.roarRepository = roarRepository;
    }

    @Override
    public List<Roar> getAllRoars() {
        return roarRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional
    public Roar addRoar(String content, String username, String userId) {
        Roar roar = new Roar(content, username, userId);
        return roarRepository.save(roar);
    }

    @Override
    @Transactional
    public Roar likeRoar(Long roarId) {
        return roarRepository.findById(roarId)
                .map(roar -> {
                    roar.setLikes(roar.getLikes() + 1);
                    return roarRepository.save(roar);
                })
                .orElseThrow(() -> new RuntimeException("Roar não encontrado com ID: " + roarId));
    }
}
