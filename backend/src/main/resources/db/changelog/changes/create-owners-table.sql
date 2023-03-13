--liquibase formatted sql
--changeset <postgres>:<create-owners-table>

CREATE TABLE IF NOT EXISTS owners
(
    id bigserial PRIMARY KEY
);

--rollback DROP TABLE owners;