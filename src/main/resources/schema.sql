CREATE TABLE IF NOT EXISTS vaccine_type
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY,
    vaccine_type_name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vaccine
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY,

    research_name     VARCHAR(255) NOT NULL,
    manufacturer_name VARCHAR(255) NOT NULL,

    vaccine_type      VARCHAR(255) NOT NULL,

    number_of_shots   INT          NOT NULL,
    available_doses   INT          NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS side_effect
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY,

    short_description VARCHAR(255) NOT NULL,
    description       TEXT         NOT NULL,

    frequency         INT          NOT NULL,
    vaccine_id        INT          NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (vaccine_id) REFERENCES vaccine (id)
);

CREATE TABLE IF NOT EXISTS user
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,

    username    VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,

    first_name  VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,

    authorityId INT          NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS authority
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_authority
(
    user_id      BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_authority FOREIGN KEY (authority_id) REFERENCES authority (id)
);





