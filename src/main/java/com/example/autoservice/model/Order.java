package com.example.autoservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    private String description;
    @Column(name = "accept_time")
    private LocalDateTime acceptDate;
    @OneToMany(mappedBy = "order")
    private List<Favor> favors;
    @ManyToMany
    @JoinTable(name = "orders_commodities",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "commodity_id"))
    private List<Commodity> commodities;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal price;
    @Column(name = "end_time")
    private LocalDateTime endDate;

    public enum Status {
        ACCEPT,
        PROGRESS,
        COMPLETED,
        NOT_COMPLETED,
        PAID
    }
}
