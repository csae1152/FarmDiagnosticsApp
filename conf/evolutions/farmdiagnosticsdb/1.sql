# --- !Ups

CREATE TABLE Person (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    age int NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE Person;
