--liquibase formatted sql
--changeset <postgres>:<insert-default-favors-price.sql>

insert into favors (price) values (500);

--rollback DELETE FROM favors WHERE id = 1;