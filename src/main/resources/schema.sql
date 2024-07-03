CREATE TABLE IF NOT EXISTS Run (
    id int NOT NULL,
    title varchar(250)  NOT NULL,
    started_on timestamp NOT NULL,
    completed_on timestamp NOT NULL,
    miles int NOT NULL,
    location varchar(1000) NOT NULL,
    PRIMARY KEY (id)
);