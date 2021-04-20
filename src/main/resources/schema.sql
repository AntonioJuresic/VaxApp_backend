CREATE TABLE IF NOT EXISTS Vaccine (
    researchName VARCHAR(255) NOT NULL,
    manufacturerName VARCHAR(255) NULL,
    vaccineType VARCHAR(255) NULL,
    numberOfDoses INT NULL,
    availableDoses INT NULL,
    PRIMARY KEY (researchName));
