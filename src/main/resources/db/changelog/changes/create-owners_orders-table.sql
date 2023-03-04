--liquibase formatted sql
--changeset <postgres>:<create-owners_orders-table>

CREATE TABLE IF NOT EXISTS owners_orders
(
    owner_id bigint not null CONSTRAINT owners_orders_oo_fk
    REFERENCES owners,
    order_id bigint not null CONSTRAINT order_uq UNIQUE
    REFERENCES orders
);
--rollback DROP TABLE owners_orders;