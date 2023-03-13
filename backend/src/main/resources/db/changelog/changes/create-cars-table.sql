--liquibase formatted sql
--changeset <postgres>:<create-cars-table>

CREATE TABLE IF NOT EXISTS cars
(
    id       bigserial PRIMARY KEY,
    brand    varchar(255),
    model    varchar(255),
    year     integer not null,
    number   varchar(255),
    owner_id bigint CONSTRAINT cars_fk REFERENCES owners
    );

--rollback DROP TABLE cars;