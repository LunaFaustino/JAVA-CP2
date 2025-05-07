package br.com.fiap.roardemo.service;

import br.com.fiap.roardemo.model.Roar;

import java.util.List;

public interface RoarService {

    List<Roar> getAllRoars();

    Roar addRoar(String content, String username, String userId);

    Roar likeRoar(Long roarId);
}
