package com.example.autoservice.service.impl;

import com.example.autoservice.model.Favor;
import com.example.autoservice.repository.FavorRepository;
import com.example.autoservice.service.FavorService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FavorServiceImpl implements FavorService {
    private final FavorRepository favorRepository;

    public FavorServiceImpl(FavorRepository favorRepository) {
        this.favorRepository = favorRepository;
    }

    @Override
    public List<Favor> getAll() {
        return favorRepository.findAll();
    }

    @Override
    public Favor get(Long id) {
        return favorRepository.getReferenceById(id);
    }

    @Override
    public Favor add(Favor favor) {
        return favorRepository.save(favor);
    }

    @Override
    public Favor update(Favor favor) {
        return favorRepository.save(favor);
    }

    @Override
    public Favor updateStatus(Long id, Favor.Status status) {
        Favor favor = favorRepository.getReferenceById(id);
        favor.setStatus(status);
        return update(favor);
    }
}
