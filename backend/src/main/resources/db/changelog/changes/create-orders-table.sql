--liquibase formatted sql
--changeset <postgres>:<create-orders-table>

CREATE TABLE IF NOT EXISTS orders
(
    id bigserial PRIMARY KEY,
    accept_time timestamp,
    description varchar(255),
    end_time timestamp,
    price numeric(19, 2),
    status varchar(255),
    car_id bigint CONSTRAINT orders_fk REFERENCES cars
    );

--rollback DROP TABLE orders;