--liquibase formatted sql
--changeset <postgres>:<create-favors-table>

CREATE TABLE IF NOT EXISTS favors
(
    id        bigserial PRIMARY KEY,
    price     numeric(19, 2),
    status    varchar(255),
    master_id bigint CONSTRAINT favors_f_fk REFERENCES masters,
    order_id  bigint CONSTRAINT favors_o_fk REFERENCES orders
    );

--rollback DROP TABLE favors;