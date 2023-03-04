--liquibase formatted sql
--changeset <postgres>:<create-masters_orders-table>

CREATE TABLE IF NOT EXISTS masters_orders
(
    master_id bigint NOT NULL CONSTRAINT master_fk REFERENCES masters,
    order_id bigint NOT NULL CONSTRAINT order_fk REFERENCES orders
);

--rollback DROP TABLE masters_orders;