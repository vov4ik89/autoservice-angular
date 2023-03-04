package com.example.autoservice.service.impl;

import com.example.autoservice.model.Favor;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import com.example.autoservice.repository.MasterRepository;
import com.example.autoservice.service.FavorService;
import com.example.autoservice.service.MasterService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    private static final double SALARY_PERCENT = 0.4;
    private final MasterRepository masterRepository;
    private final FavorService favorService;

    public MasterServiceImpl(MasterRepository masterRepository,
                             FavorService favorService) {
        this.masterRepository = masterRepository;
        this.favorService = favorService;
    }

    @Override
    public List<Master> getAll() {
        return masterRepository.findAll();
    }

    @Override
    public Master get(Long id) {
        return masterRepository.getReferenceById(id);
    }

    @Override
    public Master add(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master update(Master master) {
        Master oldMaster = get(master.getId());
        master.setCompletedOrders(oldMaster.getCompletedOrders());
        return masterRepository.save(master);
    }

    @Override
    public List<Master> update(Iterable<Master> masters) {
        return masterRepository.saveAll(masters);
    }

    @Override
    public Set<Order> getOrders(Long masterId) {
        return masterRepository.getReferenceById(masterId).getCompletedOrders();
    }

    @Override
    public BigDecimal getSalary(Long masterId) {
        return get(masterId).getCompletedOrders().stream()
                .flatMap(order -> order.getFavors().stream())
                .filter(favor -> favor.getStatus().equals(Favor.Status.NOT_PAID))
                .peek(favor -> favorService.updateStatus(favor.getId(), Favor.Status.PAID))
                .map(Favor::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(new BigDecimal(SALARY_PERCENT));
    }
}
