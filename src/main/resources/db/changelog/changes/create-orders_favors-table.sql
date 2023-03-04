--liquibase formatted sql
--changeset <postgres>:<create-orders_favors-table>

CREATE TABLE IF NOT EXISTS orders_favors
(
    order_id   bigint not null CONSTRAINT orders_f_o_fk REFERENCES orders,
    favors_id bigint not null CONSTRAINT favors_uq UNIQUE
    CONSTRAINT orders_f_f_fk REFERENCES favors
);

--rollback DROP TABLE orders_favors;