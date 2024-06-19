-- Inserting 5 Customers
INSERT INTO Customer (username, name, address) VALUES
('user1', 'Name1', 'Address1'),
('user2', 'Name2', 'Address2'),
('user3', 'Name3', 'Address3'),
('user4', 'Name4', 'Address4'),
('user5', 'Name5', 'Address5');


-- Inserting 5 Fields
INSERT INTO Field (available, location, name, price_eur, price_sek) VALUES
(false, 'Location1', 'Field1', 40.0, 400.0 ),
(false, 'Location2', 'Field2',30.0, 300.0),
(true, 'Location3', 'Field3', 25.0, 250.0),
(true, 'Location4', 'Field4', 15.0, 150.0),
(true, 'Location5', 'Field5', 45.0, 450.0);

-- Inserting 5 Bookings
INSERT INTO Booking (is_active,number_of_players, price_total_sek, price_total_eur, customer_id, start_date_and_time, end_date_and_time, field_id, booking_id) VALUES
(false,2, 400.0, 40.0, 1 ,'2024-06-10T10:00:00', '2024-06-10T11:00:00' ,1, 1),
(false,2, 300.0, 30.0, 2, '2024-06-11T11:00:00','2024-06-11T12:00:00' ,2, 2),
(true,4, 250.0, 25.0, 3, '2024-06-12T13:00:00', '2024-06-12T14:00:00',3, 3),
(true,4, 150.0, 15.0, 4, '2024-06-13T14:00:00','2024-06-13T15:00:00' ,4, 4),
(true,4, 450, 45.0, 5,'2024-06-14T15:00:00', '2024-06-14T16:00:00',5, 5);

