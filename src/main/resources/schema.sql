CREATE TABLE IF NOT EXISTS VaccineType (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    vaccineTypeName VARCHAR(255) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Vaccine (
    id BIGINT GENERATED ALWAYS AS IDENTITY,

    researchName VARCHAR(255) NOT NULL,
    manufacturerName VARCHAR(255) NOT NULL,

    vaccineType VARCHAR(255) NOT NULL,

    numberOfDoses INT NOT NULL,
    availableDoses INT NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS SideEffect (
    id BIGINT GENERATED ALWAYS AS IDENTITY,

    shortDescription VARCHAR(255) NOT NULL,
    longDescription TEXT NOT NULL,

    frequency INT NOT NULL,
    vaccineId INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (vaccineId) REFERENCES Vaccine(id)
);
