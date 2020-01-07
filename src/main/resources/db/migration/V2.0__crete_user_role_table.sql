CREATE TABLE role (
    id                   SERIAL NOT NULL,
    name                 VARCHAR(30) not null unique,
    allowed_resource     VARCHAR(200),
    allowed_read         BOOLEAN default false,
    allowed_create       BOOLEAN default false,
    allowed_update       BOOLEAN default false,
    allowed_delete       BOOLEAN default false
);

ALTER TABLE role ADD CONSTRAINT role_pk PRIMARY KEY ( id );

CREATE TABLE customer_role (
    customer_id    INTEGER NOT NULL,
    role_id    INTEGER NOT NULL
);

ALTER TABLE customer_role
    ADD CONSTRAINT customer_fk FOREIGN KEY ( customer_id )
        REFERENCES customer ( id );

ALTER TABLE customer_role
    ADD CONSTRAINT role_fk FOREIGN KEY ( role_id )
        REFERENCES role ( id );