INSERT INTO Vaccine (id, researchName, manufacturerName, vaccineType, numberOfDoses, availableDoses)
VALUES (1, 'AZD1222', 'Astra Zeneca', 'VIRAL_VECTOR', 1, 2000),
       (2, 'BNT162b2', 'Pfizer-BioNTech', 'RNA', 2, 42000),
       (3, 'mRNA-1273', 'Moderna', 'RNA', 2, 50000),
       (4, 'JNJ-78436735', 'Johnson & Johnson', 'VIRAL_VECTOR', 3, 40000),
       (5, 'BBIBP-CorV', 'Sinopharm', 'VIRAL_VECTOR', 2, 40000);

INSERT INTO SideEffect(shortDescription, longDescription, frequency, vaccineId)
VALUES ('Crvenilo', 'Crvenilo kod ušiju.', 10, 1),
       ('Glavobolja', 'Glavobolja koja traje tri dana.', 10, 1);
