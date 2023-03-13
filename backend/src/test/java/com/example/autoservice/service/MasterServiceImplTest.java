package com.example.autoservice.service;

import com.example.autoservice.model.Favor;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import com.example.autoservice.repository.MasterRepository;
import com.example.autoservice.service.impl.MasterServiceImpl;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MasterServiceImplTest {
    @InjectMocks
    private MasterServiceImpl masterService;
    @Mock
    private MasterRepository masterRepository;

    @Test
    void calculateSalary() {
        Long masterId = 1L;
        Favor.Status favorStatus = Favor.Status.NOT_PAID;
        Favor firstRepair = new Favor();
        firstRepair.setPrice(new BigDecimal(100));
        firstRepair.setStatus(favorStatus);
        Favor secondRepair = new Favor();
        secondRepair.setPrice(new BigDecimal(200));
        secondRepair.setStatus(favorStatus);
        Order order = new Order();
        order.setFavors(List.of(firstRepair, secondRepair));
        Master master = new Master();
        master.setCompletedOrders(Set.of(order));
        Mockito.when(masterRepository.getReferenceById(masterId)).thenReturn(master);
        BigDecimal expected = new BigDecimal("120.00");
        BigDecimal actual = masterService.getSalary(masterId);
        Assertions.assertEquals(expected, actual.round(new MathContext(5)));
    }
}