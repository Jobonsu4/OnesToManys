-- Insert data into dog_shelter table
INSERT INTO dog_shelter (name, address, phone_number, email) VALUES
('Happy Paws Shelter', '123 Main St, Springfield', '555-1234', 'contact@happypaws.com'),
('Furry Friends Haven', '456 Oak Ave, Shelbyville', '555-5678', 'info@furryfriends.com'),
('Paws & Claws Rescue', '789 Pine Rd, Capital City', '555-9012', 'support@pawsclaws.org');

-- Insert data into dog table
INSERT INTO dog (name, breed, age, gender, description, shelter_id) VALUES
('Buddy', 'Golden Retriever', 3, 'Male', 'Friendly and energetic', 1),
('Lucy', 'Beagle', 2, 'Female', 'Loves to play fetch', 1),
('Max', 'German Shepherd', 4, 'Male', 'Loyal and protective', 2),
('Bella', 'Labrador', 1, 'Female', 'Very playful and loving', 2),
('Charlie', 'Poodle', 5, 'Male', 'Calm and well-behaved', 3),
('Daisy', 'Bulldog', 3, 'Female', 'Gentle and affectionate', 3);
