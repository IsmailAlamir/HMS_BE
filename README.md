# HMS_BE
## Data Base
#### SQL Commnad
**Create users for each role**

```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (1, 'admin@example.com', 'John', 'Doe', 'Admin Location', 'admin123', '1234567890', 'ADMIN', 'admin');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (2, 'patient@example.com', 'Alice', 'Smith', 'Patient Location', 'patient123', '0987654321', 'PATIENT', 'alice');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (3, 'doctor@example.com', 'Michael', 'Johnson', 'Doctor Location', 'doctor123', '5678901234', 'DOCTOR', 'michael');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (4, 'pharmacist@example.com', 'Emily', 'Brown', 'Pharmacist Location', 'pharmacist123', '4321098765', 'PHARMACIST', 'emily');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (5, 'lab@example.com', 'David', 'Miller', 'Lab Location', 'lab123', '6789012345', 'LAB', 'david');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (6, 'xray@example.com', 'Sophia', 'Wilson', 'X-Ray Location', 'xray123', '3210987654', 'XRAY', 'sophia');
```

**Insert records into other tables**
```
INSERT INTO medicine (id, names, doses, add_date, result_date)
VALUES (1, 'Medicine A', 10, '2023-05-19', '2023-05-20');
```
```
INSERT INTO test (id, names, folder_url, add_date, result_date)
VALUES (1, 'Test A', '/test_folder/testA', '2023-05-20', '2023-05-25');
```
```
INSERT INTO patient (user_id, gender, birthday, height, weight, blood_type, allergies)
VALUES (2,'Female', '1993-05-19', 160, 60, 'O+', 'None');
```
```
INSERT INTO visit (id, patient_id, doctor_id, pharmacist_id, lab_id, x_ray_id, summary, description, prescription, medicine_id, test_id, x_rays_image_id)
VALUES (1, 1, 3, 4, 5, 6, 'Visit summary', 'Visit description', 123, 1, 1, 1);
```
```
INSERT INTO xray (id, names, folder_url, add_date, result_date)
VALUES (1, 'XRay A', '/xray_folder/xrayA.jpg', '2023-05-19', '2023-05-24');
```
#### check the tables
```
SELECT * FROM _user;
```
```
SELECT * FROM medicine;
```
```
SELECT * FROM patient;
```
```
SELECT * FROM test;
```
```
SELECT * FROM visit;
```
```
SELECT * FROM xray;
```
// todo : auth , permission  
