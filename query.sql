CREATE TABLE developers
(
    id        SERIAL        NOT NULL PRIMARY KEY,
    name      VARCHAR(20)   NOT NULL,
    specialty VARCHAR(20)   NOT NULL,
    salary    NUMERIC(8, 2) NOT NULL
);

INSERT INTO developers (name, specialty, salary)
VALUES ('Alexander', 'Java', 3000);
INSERT INTO developers (name, specialty, salary)
VALUES ('Igor', 'Php', 2000);
INSERT INTO developers (name, specialty, salary)
VALUES ('Fedor', 'Frontend', 4000);

drop table if exists cat;
create table cat(
                    id serial primary key,
                    name text,
                    color text
);
insert into cat(name, color)
values
    ('Alvin', 'Brown'),
    ('Casper', 'Gray'),
    ('Bella', 'Cream'),
    ('Dana', 'White'),
    ('Aron', 'Tortoiseshell');