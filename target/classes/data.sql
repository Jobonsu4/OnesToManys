-- Dog Shelters
INSERT INTO dog_shelter (id, name, location, email, phone_number) VALUES
(1, 'Happy Tails Shelter', '123 Main St, Cityville', 'info@happytails.com', '555-1234'),
(2, 'Paws and Friends', '456 Elm St, Townsville', 'contact@pawsfriends.com', '555-5678');

-- Dogs
INSERT INTO dog (id, name, breed, age, gender, description, shelter_id) VALUES
(1, 'Buddy', 'Golden Retriever', 3, 'Male', 'Friendly and playful.', 1),
(2, 'Luna', 'Labrador', 2, 'Female', 'Loves kids and cuddles.', 1),
(3, 'Max', 'Beagle', 4, 'Male', 'Energetic and curious.', 2),
(4, 'Bella', 'German Shepherd', 5, 'Female', 'Loyal and intelligent.', 2);
