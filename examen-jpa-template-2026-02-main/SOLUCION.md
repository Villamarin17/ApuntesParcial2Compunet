# Solución de referencia: Examen JPA 2 (GitHub Classroom)

## Parte 1 (40%): errores del modelo corregidos

| Archivo | Error | Corrección |
|---|---|---|
| User | Sin `@Id` | `@Id` + `IDENTITY` |
| User | `role` mapeado a la columna `full_name` (columna duplicada) | `fullName` → `full_name`, `role` → `role` |
| User | `ownedRepositories` tipado como `List<Classroom>` con `mappedBy="teacher"`; `taughtRepositories` como `List<PullRequest>` con `mappedBy="owner"` | `taughtClassrooms` (`List<Classroom>`, mappedBy `teacher`) y `ownedRepositories` (`List<Repository>`, mappedBy `owner`) |
| User | `@ManyToOne(mappedBy=...)` sobre listas (no compila) | `@OneToMany(mappedBy="author")` |
| User | `commits` con `mappedBy="commits"` | `mappedBy="author"` |
| User | Listas sin `@JsonIgnore` (recursión infinita en el JSON) | `@JsonIgnore` en todas las listas |
| Classroom | Clase vacía: sin `@Id`, sin atributos, sin `@Getter` | Atributos completos + `@ManyToOne User teacher` (`teacher_id`) |
| Assignment | `@Id` sobre `deadline`, e `id` como `Timestamp` | `id` Long con `@Id`; `deadline` como `LocalDateTime` NOT NULL |
| Assignment | Atributo `id2` en lugar de `description` | `description` (TEXT, nullable) |
| Assignment | `@OneToMany` sobre un solo `Classroom` | `@ManyToOne` + `@JoinColumn(name="classroom_id")` |
| Assignment | `@ManyToMany(mappedBy="assignment")` sobre un solo `Repository` | `@OneToMany(mappedBy="assignment") List<Repository>` |
| Assignment, Commit | Faltaba `@Getter` | Agregado |
| Repository | Clase vacía: sin `@Id`, atributos, constructores ni `@Getter` | Atributos completos, `assignment`, `owner` y la autorrelación `parentRepository` (`parent_repo_id`, nullable) |
| PullRequest | `@OneToMany List<Repository>` con `@JoinColumn repository_id` | `@ManyToOne Repository repository` |
| PullRequest | Faltaba `author_id` | `@ManyToOne User author` |
| PullRequest | `@OneToMany User teacher` sobre `reviewer_id` | `@ManyToOne User reviewer` (nullable) |
| Commit | Faltaban `lines_added`, `lines_deleted`, `commit_date` | Agregados |
| Commit | `@OneToMany List<User> collaborators` sobre `author_id` | `@ManyToOne User author` |
| Commit, Assignment, PullRequest | `java.sql.Timestamp` | `LocalDateTime` (API moderna de fechas) |
| data.sql | El INSERT de `classrooms` traía 5 valores pero solo 4 columnas (faltaba `teacher_id`) | Se agregó `teacher_id` a la lista de columnas |
| data.sql | Insertaba `assignments` antes que `classrooms` y `pull_requests` antes que `repositories` (falla por FK) | Se reordenó: primero las tablas padre |

## Parte 2 (60%): consultas (las 5 implementadas; se califican las 4 mejores)

Base URL: `http://localhost:3001/compunet2-2026`

| # | Endpoint de prueba | Resultado esperado |
|---|---|---|
| 1 | `/pull-requests/by-classroom?classroomName=Computacion en Internet II - Grupo 1&status=OPEN` | Entrega Taller JPA - Valeria Mendoza, Entrega Taller JPA - Camila Jimenez |
| 2 | `/repositories/derived?teacherEmail=krodriguez@icesi.edu.co&deadlineAfter=2026-03-15 00:00:00` | jpa-exam-cjimenez, jpa-exam-dcastillo, jpa-exam-vmendoza, microservices-cjimenez |
| 3 | `/pull-requests/by-reviewer-role?reviewerRole=TA&authorUsername=cjimenez&semester=2026-02` | Entrega Taller JPA - Camila Jimenez, Correcciones finales JPA - Camila Jimenez |
| 4 | `/commits/by-template?templateName=template-jpa-exam&keyword=fix&minLinesAdded=50` | e4f5g6h, i7j8k9l, m1n2o3p |
| 5 | `/assignments/by-teacher-reviewer?teacherUsername=krodriguez&reviewerUsername=jvalencia&status=MERGED` | Taller 1: Spring Data JPA |

El navegador convierte los espacios de las URLs 1 y 2 en `%20` automáticamente.

Consola H2: `http://localhost:3001/compunet2-2026/h2-console` (JDBC URL `jdbc:h2:mem:db-github`, usuario `sa`).
