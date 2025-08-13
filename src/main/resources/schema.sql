-- Dog Shelter table
CREATE TABLE dog_shelter (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(255)
);

-- Dog table
CREATE TABLE dog (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    breed VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    description TEXT,
    shelter_id BIGINT,
    CONSTRAINT fk_shelter FOREIGN KEY (shelter_id) REFERENCES dog_shelter(id) ON DELETE CASCADE
);
