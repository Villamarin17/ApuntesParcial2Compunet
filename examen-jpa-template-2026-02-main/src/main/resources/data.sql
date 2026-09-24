-- ==========================================================
-- SCRIPT DE INICIALIZACIÓN DE DATOS: GITHUB CLASSROOM
-- ==========================================================
-- FIX: classrooms insert was missing the teacher_id column
-- FIX: tables are inserted parent-first so every foreign key already exists

INSERT INTO users (id, username, email, full_name, role) VALUES
(1, 'krodriguez', 'krodriguez@icesi.edu.co', 'Kevin Rodriguez', 'TEACHER'),
(2, 'amunoz', 'amunoz@icesi.edu.co', 'Alejandro Munoz', 'TEACHER'),
(3, 'drincon', 'drincon@icesi.edu.co', 'Domiciano Rincon', 'TEACHER'),
(4, 'jvalencia', 'jvalencia@icesi.edu.co', 'Juan Valencia', 'TA'),
(5, 'sperez', 'sperez@icesi.edu.co', 'Santiago Perez', 'TA'),
(6, 'cjimenez', 'cjimenez@u.icesi.edu.co', 'Camila Jimenez', 'STUDENT'),
(7, 'dcastillo', 'dcastillo@u.icesi.edu.co', 'Daniel Castillo', 'STUDENT'),
(8, 'vmendoza', 'vmendoza@u.icesi.edu.co', 'Valeria Mendoza', 'STUDENT'),
(9, 'mguerrero', 'mguerrero@u.icesi.edu.co', 'Mateo Guerrero', 'STUDENT'),
(10, 'srestrepo', 'srestrepo@u.icesi.edu.co', 'Sofia Restrepo', 'STUDENT');

INSERT INTO classrooms (id, name, course_code, semester, teacher_id) VALUES
(1, 'Computacion en Internet II - Grupo 1', 'CI-2026-G1', '2026-02', 1),
(2, 'Computacion en Internet II - Grupo 2', 'CI-2026-G2', '2026-02', 2),
(3, 'Arquitectura de Software - Grupo 1', 'AS-2026-G1', '2026-02', 1),
(4, 'Sistemas Distribuidos', 'SD-2025-G1', '2025-02', 3);

INSERT INTO assignments (id, title, description, deadline, max_score, classroom_id) VALUES
(1, 'Taller 1: Spring Data JPA', 'Implementacion de modelos relacionales y Query Methods', '2026-03-20 23:59:59', 100, 1),
(2, 'Parcial 1: Persistencia Avanzada', 'Examen practico de JPA con relaciones complejas', '2026-04-10 18:00:00', 100, 1),
(3, 'Laboratorio REST APIs', 'Diseno e implementacion de controladores RESTful', '2026-03-25 23:59:59', 100, 2),
(4, 'Proyecto Final: Microservicios', 'Arquitectura modular orientada a eventos', '2026-05-30 23:59:59', 100, 3),
(5, 'Laboratorio RPC Antiguo', 'Practicas de gRPC del semestre anterior', '2025-03-10 23:59:59', 100, 4);

INSERT INTO repositories (id, name, is_template, is_private, created_at, assignment_id, owner_id, parent_repo_id) VALUES
(1, 'template-jpa-exam', true, false, '2026-03-01 08:00:00', 1, 1, NULL),
(2, 'template-rest-api', true, false, '2026-03-02 08:00:00', 3, 2, NULL),
(3, 'template-microservices', true, false, '2026-03-05 08:00:00', 4, 1, NULL),
(4, 'jpa-exam-cjimenez', false, true, '2026-03-10 09:30:00', 1, 6, 1),
(5, 'jpa-exam-dcastillo', false, true, '2026-03-10 10:15:00', 1, 7, 1),
(6, 'jpa-exam-vmendoza', false, true, '2026-03-11 11:00:00', 1, 8, 1),
(7, 'rest-api-mguerrero', false, true, '2026-03-12 14:20:00', 3, 9, 2),
(8, 'rest-api-srestrepo', false, true, '2026-03-13 16:45:00', 3, 10, 2),
(9, 'microservices-cjimenez', false, true, '2026-03-15 17:00:00', 4, 6, 3),
(10, 'standalone-notes-dcastillo', false, false, '2026-03-01 12:00:00', 1, 7, NULL);

INSERT INTO pull_requests (id, title, status, pr_number, created_at, repository_id, author_id, reviewer_id) VALUES
(1, 'Entrega Taller JPA - Camila Jimenez', 'OPEN', 1, '2026-03-18 19:30:00', 4, 6, 4),
(2, 'Entrega Taller JPA - Daniel Castillo', 'MERGED', 1, '2026-03-17 14:10:00', 5, 7, 4),
(3, 'Entrega Taller JPA - Valeria Mendoza', 'OPEN', 1, '2026-03-19 22:00:00', 6, 8, 1),
(4, 'Entrega REST - Mateo Guerrero', 'MERGED', 1, '2026-03-22 11:45:00', 7, 9, 5),
(5, 'Entrega REST - Sofia Restrepo', 'CLOSED', 1, '2026-03-23 09:15:00', 8, 10, 2),
(6, 'Avance Microservicios - Camila Jimenez', 'OPEN', 1, '2026-03-24 16:00:00', 9, 6, 1),
(7, 'Correcciones finales JPA - Camila Jimenez', 'MERGED', 2, '2026-03-19 21:00:00', 4, 6, 4);

INSERT INTO commits (id, commit_hash, message, lines_added, lines_deleted, commit_date, repository_id, author_id) VALUES
(1, 'a1b2c3d', 'Initial commit with starter code', 150, 0, '2026-03-01 08:10:00', 1, 1),
(2, 'e4f5g6h', 'Feature: add Entity mappings and fix relations', 180, 20, '2026-03-12 10:00:00', 4, 6),
(3, 'i7j8k9l', 'Fix: solve cyclic reference and add query methods', 95, 12, '2026-03-14 15:30:00', 4, 6),
(4, 'm1n2o3p', 'Refactor: complete repository queries and fix tests', 120, 15, '2026-03-15 18:00:00', 5, 7),
(5, 'q4r5s6t', 'Fix: adjust model annotations', 40, 5, '2026-03-16 11:20:00', 6, 8),
(6, 'u7v8w9x', 'Feature: implement controllers and endpoints', 210, 30, '2026-03-21 16:40:00', 7, 9),
(7, 'y1z2a3b', 'Docs: update readme and documentation', 25, 2, '2026-03-22 10:15:00', 8, 10),
(8, 'c4d5e6f', 'Feature: initial setup for microservices architecture', 350, 10, '2026-03-23 14:00:00', 9, 6);
