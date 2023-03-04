--liquibase formatted sql
--changeset <postgres>:<create-orders_commodities-table>

CREATE TABLE IF NOT EXISTS orders_commodities
(
    order_id bigint not null CONSTRAINT order_c_fk REFERENCES orders,
    commodity_id bigint not null CONSTRAINT uq_commodity UNIQUE
    CONSTRAINT commodity_c_fk REFERENCES commodities
);

--rollback DROP TABLE orders_commodities;