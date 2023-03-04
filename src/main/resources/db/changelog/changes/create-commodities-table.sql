--liquibase formatted sql
--changeset <postgres>:<create-commodities-table>

CREATE TABLE IF NOT EXISTS commodities
(
    id    bigserial PRIMARY KEY,
    name  varchar(255),
    price numeric
    );

--rollback DROP TABLE commodities;