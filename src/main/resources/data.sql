-- Dog Shelters
INSERT INTO dog_shelter (id, name, location, email, phone_number) VALUES
(1, 'Happy Tails Shelter', '123 Main St, Cityville', 'info@happytails.com', '555-1234'),
(2, 'Paws and Friends', '456 Elm St, Townsville', 'contact@pawsfriends.com', '555-5678');
-- Inserts two initial shelters into the dog_shelter table
-- Each record provides:
--   id: unique identifier matching the primary key
--   name: shelter name
--   location: physical address
--   email: contact email
--   phone_number: contact phone number

-- Dogs
INSERT INTO dog (id, name, breed, age, gender, description, shelter_id) VALUES
(1, 'Buddy', 'Golden Retriever', 3, 'Male', 'Friendly and playful.', 1),
(2, 'Luna', 'Labrador', 2, 'Female', 'Loves kids and cuddles.', 1),
(3, 'Max', 'Beagle', 4, 'Male', 'Energetic and curious.', 2),
(4, 'Bella', 'German Shepherd', 5, 'Female', 'Loyal and intelligent.', 2);
-- Inserts four initial dogs into the dog table
-- Each record provides:
--   id: unique identifier for the dog
--   name: dog’s name
--   breed: dog’s breed
--   age: dog’s age in years
--   gender: dog’s gender
--   description: brief personality/behavior description
--   shelter_id: foreign key referencing the shelter the dog belongs to
-- The shelter_id ensures each dog is linked to one of the shelters above
