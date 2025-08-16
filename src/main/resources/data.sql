-- ============================
-- DOG SHELTERS
-- ============================
INSERT INTO dog_shelter (id, name, location, email, phone_number) VALUES
(1, 'Happy Tails Shelter', '123 Main St, Cityville', 'info@happytails.com', '555-1234'),
(2, 'Paws and Friends', '456 Elm St, Townsville', 'contact@pawsfriends.com', '555-5678'),
(3, 'Furry Friends Haven', '789 Oak St, Villagetown', 'contact@furryfriends.com', '555-9012'),
(4, 'Safe Paws Shelter', '321 Pine St, Hamletville', 'hello@safepaws.com', '555-3456'),
(5, 'Lucky Dogs Rescue', '654 Maple St, Metrocity', 'support@luckydogs.com', '555-7890'),
(6, 'Forever Home Shelter', '987 Cedar St, Riverdale', 'team@foreverhome.com', '555-2345'),
(7, 'Tail Waggers Haven', '111 Birch St, Lakeside', 'info@tailwaggers.com', '555-6789'),
(8, 'Puppy Paradise', '222 Walnut St, Meadowbrook', 'contact@puppyparadise.com', '555-1122'),
(9, 'Second Chance Rescue', '333 Chestnut St, Greenfield', 'hello@secondchance.com', '555-3344'),
(10, 'Happy Paws Shelter', '444 Spruce St, Hilltown', 'support@happypaws.com', '555-5566');

-- ============================
-- DOGS
-- ============================
INSERT INTO dog (id, name, breed, age, gender, description, shelter_id) VALUES
(1, 'Pucci', 'Golden Retriever', 3, 'Male', 'Friendly and playful.', 1),
(2, 'Luna', 'Labrador', 2, 'Female', 'Loves kids and cuddles.', 1),
(3, 'Max', 'Cane Corso', 4, 'Male', 'Energetic and curious.', 2),
(4, 'Jamie', 'German Shepherd', 5, 'Female', 'Loyal and intelligent.', 2),
(5, 'Julio', 'Poodle', 2, 'Male', 'Loves to play fetch.', 3),
(6, 'Daisy', 'Bulldog', 4, 'Female', 'Very calm and friendly.', 3),
(7, 'Spike', 'Boxer', 3, 'Male', 'High energy and strong.', 4),
(8, 'Molly', 'Shih Tzu', 1, 'Female', 'Cute and cuddly.', 4),
(9, 'Jacksparrow', 'Rottweiler', 6, 'Male', 'Protective and loyal.', 5),
(10, 'Sadie', 'Cocker Spaniel', 3, 'Female', 'Gentle and loving.', 5),
(11, 'Luca', 'Husky', 4, 'Male', 'Very playful and smart.', 6),
(12, 'Mario', 'Dalmatian', 2, 'Female', 'Energetic and friendly.', 6),
(13, 'Scooby', 'Beagle', 5, 'Male', 'Loves outdoor activities.', 7),
(14, 'Lola', 'Pomeranian', 1, 'Female', 'Tiny but energetic.', 7),
(15, 'Zeus', 'Doberman', 6, 'Male', 'Strong and protective.', 8),
(16, 'Chloe', 'Shiba Inu', 3, 'Female', 'Independent and smart.', 8),
(17, 'Maverick', 'German Shepherd', 4, 'Male', 'Disciplined and alert.', 9),
(18, 'Nala', 'Labrador', 2, 'Female', 'Friendly and loves water.', 9),
(19, 'Oliver', 'Corgi', 3, 'Male', 'Playful and social.', 10),
(20, 'Sophie', 'Golden Retriever', 5, 'Female', 'Loving and calm.', 10);
