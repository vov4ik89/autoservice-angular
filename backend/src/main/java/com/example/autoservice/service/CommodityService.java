package com.example.autoservice.service;

import com.example.autoservice.model.Commodity;
import java.util.List;

public interface CommodityService {
    List<Commodity> getAll();

    Commodity get(Long id);

    Commodity add(Commodity commodity);

    Commodity update(Commodity commodity);
}
