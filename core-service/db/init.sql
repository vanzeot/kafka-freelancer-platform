-- Reset completo
DROP TABLE IF EXISTS task_skills;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS owner;

-- Owner
CREATE TABLE owner (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       average_rating FLOAT DEFAULT 0
);

-- Task
CREATE TABLE task (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description TEXT,
                      owner_id INTEGER REFERENCES owner(id),
                      assigned_freelancer_id INTEGER,
                      estimated_time INTEGER,
                      concluded_time INTEGER,
                      estimated_start_date TIMESTAMP,
                      estimated_finish_date TIMESTAMP,
                      started_date TIMESTAMP,
                      finished_date TIMESTAMP,
                      owner_review_score INTEGER,
                      owner_review_note VARCHAR(255),
                      status VARCHAR(50),
                      created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Task skills
CREATE TABLE task_skills (
                             task_id INTEGER REFERENCES task(id),
                             skills VARCHAR(50)
);

-- Dados iniciais
INSERT INTO owner (name, email) VALUES ('Vanz Soft', 'comercial@vanzsoft.com.br');
INSERT INTO owner (name, email) VALUES ('Santos Co.', 'comercial@santosco.com.br');

INSERT INTO task (title, description, owner_id, status) VALUES
    ('Kafka Back-end for Finances', 'CRUD + 8 custom endpoints using kafka and springboot.', 1, 'PUBLISHED');
INSERT INTO task_skills (task_id, skills) VALUES (1, 'BACKEND');

INSERT INTO task (title, description, owner_id, status) VALUES
    ('Angular Dashboard', 'Interactive dashboard for finances.', 1, 'DRAFT');
INSERT INTO task_skills (task_id, skills) VALUES (2, 'JAVASCRIPT'), (2, 'FRONTEND');

INSERT INTO task (title, description, owner_id, status) VALUES
    ('Feature for Mobile APP using IONIC', 'Develop a custom date-picker component.', 2, 'ASSIGNED');
INSERT INTO task_skills (task_id, skills) VALUES (3, 'JAVASCRIPT');
