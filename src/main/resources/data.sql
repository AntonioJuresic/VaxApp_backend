INSERT INTO Vaccine (id, research_name, manufacturer_name, vaccine_type, number_of_shots, available_doses)
VALUES (1, 'AZD1222', 'Astra Zeneca', 'VIRAL_VECTOR', 1, 2000),
       (2, 'BNT162b2', 'Pfizer-BioNTech', 'MRNA', 2, 42000),
       (3, 'MRNA-1273', 'Moderna', 'MRNA', 2, 50000),
       (4, 'JNJ-78436735', 'Johnson & Johnson', 'VIRAL_VECTOR', 3, 40000),
       (5, 'BBIBP-CorV', 'Sinopharm', 'VIRAL_VECTOR', 2, 40000);

INSERT INTO side_effect(short_description, description, frequency, vaccine_id)
VALUES ('Crvenilo', 'Crvenilo kod ušiju.', 10, 1),
       ('Glavobolja', 'Glavobolja koja traje tri dana.', 10, 1),

       ('Mučnina', 'Mučnina koja traje dva dana.', 20, 2),
       ('Crvenilo', 'Crvenilo kože.', 50, 2),
       ('Glavobolja', 'Glavobolja koja traje tri dana.', 6, 2),

       ('Glavobolja', 'Glavobolja koja traje tri dana.', 10, 3);

INSERT INTO authority(id, name)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO user(id, username, password, first_name, last_name, authorityId)
VALUES (1, 'admin', '$2y$12$MwzAZ2GRjhvCN9.H.PM.UOEltjJxCseHpewx4JUViFq7RZ79uiOoi', 'admin', 'admin', 1),
       (2, 'user', '$2y$12$MwzAZ2GRjhvCN9.H.PM.UOEltjJxCseHpewx4JUViFq7RZ79uiOoi', 'user', 'user', 2);

INSERT INTO user_authority(user_id, authority_id)
VALUES (1, 1),
       (2, 2);
