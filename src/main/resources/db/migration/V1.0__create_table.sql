CREATE TABLE business (
   id                SERIAL NOT NULL,
   name              VARCHAR(30) not null unique,
   address       VARCHAR(150),
   category          VARCHAR(100),
   hours        VARCHAR (250)

);
ALTER TABLE business ADD CONSTRAINT business_pk PRIMARY KEY ( id );
CREATE TABLE customer (
   id              SERIAL NOT NULL,
   name            VARCHAR(30) not null unique,
   email           VARCHAR(50),
   address         VARCHAR(150),
   age              INTEGER,
   password         VARCHAR(30)

);
ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( id );
CREATE TABLE review (
   id             SERIAL NOT NULL,
   b_id          INTEGER NOT NULL,
   c_id        INTEGER NOT NULL,
   rate         INTEGER,
   content      VARCHAR(200),
   create_date    date default CURRENT_DATE
);
ALTER TABLE review ADD CONSTRAINT review_pk PRIMARY KEY ( id );
ALTER TABLE review
   ADD CONSTRAINT review_customer_fk FOREIGN KEY ( c_id )
       REFERENCES customer ( id );
ALTER TABLE review
   ADD CONSTRAINT review_business_fk FOREIGN KEY ( b_id )
       REFERENCES business ( id );