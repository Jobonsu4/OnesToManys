-- ================================
-- SCHEMA: Dog Shelter Management
-- ================================
-- Just a comment describing that this SQL file defines the database schema for the Dog Shelter app

-- Drop tables if they already exist (safe for development)
DROP TABLE IF EXISTS dog;
DROP TABLE IF EXISTS dog_shelter;
-- Ensures that old versions of the tables are removed before creating new ones
-- Useful during development to avoid conflicts when running the script multiple times

-- Table: dog_shelter
CREATE TABLE dog_shelter (
    id INTEGER PRIMARY KEY AUTOINCREMENT, -- Unique identifier for each shelter; automatically increments
    name TEXT NOT NULL,                   -- Shelter name, cannot be null
    location TEXT NOT NULL,               -- Shelter location/address, cannot be null
    email TEXT NOT NULL,                  -- Shelter email address, cannot be null
    phone_number TEXT NOT NULL            -- Shelter contact number, cannot be null
);

-- Table: dog
CREATE TABLE dog (
    id INTEGER PRIMARY KEY AUTOINCREMENT, -- Unique identifier for each dog; auto-incremented
    name TEXT NOT NULL,                   -- Dog's name, required
    breed TEXT NOT NULL,                  -- Dog's breed, required
    age INTEGER NOT NULL,                 -- Dog's age in years, required
    gender TEXT NOT NULL,                 -- Dog's gender, required
    description TEXT,                     -- Optional description of the dog
    shelter_id INTEGER NOT NULL,          -- Foreign key linking to the shelter this dog belongs to
    FOREIGN KEY (shelter_id) REFERENCES dog_shelter(id) ON DELETE CASCADE
    -- Ensures referential integrity: if a shelter is deleted, all associated dogs are automatically deleted
);
