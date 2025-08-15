-- ================================
-- SCHEMA: Dog Shelter Management
-- ================================

-- Drop tables if they already exist (safe for development)
DROP TABLE IF EXISTS dog;
DROP TABLE IF EXISTS dog_shelter;

-- Table: dog_shelter
CREATE TABLE dog_shelter (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    location TEXT NOT NULL,
    email TEXT NOT NULL,
    phone_number TEXT NOT NULL
);

-- Table: dog
CREATE TABLE dog (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    breed TEXT NOT NULL,
    age INTEGER NOT NULL,
    gender TEXT NOT NULL,
    description TEXT,
    shelter_id INTEGER NOT NULL,
    FOREIGN KEY (shelter_id) REFERENCES dog_shelter(id) ON DELETE CASCADE
);
