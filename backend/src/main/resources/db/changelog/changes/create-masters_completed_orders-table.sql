--liquibase formatted sql
--changeset <postgres>:<create-masters_completed_orders-table>

CREATE TABLE IF NOT EXISTS masters_completed_orders
(
    master_id bigint NOT NULL CONSTRAINT master_fk REFERENCES masters,
    completed_order_id bigint NOT NULL CONSTRAINT order_fk REFERENCES orders
);

--rollback DROP TABLE masters_completed_orders;