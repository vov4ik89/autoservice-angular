package com.example.autoservice.service;

import com.example.autoservice.model.Favor;
import java.util.List;

public interface FavorService {
    List<Favor> getAll();

    Favor get(Long id);

    Favor add(Favor favor);

    Favor update(Favor favor);

    Favor updateStatus(Long id, Favor.Status status);
}
