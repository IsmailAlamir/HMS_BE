# Hospital Management System (HMS)

## Routes :

### 1. User Registration:
   - POST ```/api/v1/auth/register``` - Register a new patient.

### 2. Authentication and Authorization:
   - POST ```/api/v1/auth/authenticate``` - Authenticate a user and generate an access token.
   - POST ```/api/v1/auth/logout``` - Logout a user and invalidate the access token.
   - POST ```/api/v1/auth/refresh``` - Refresh the access token using a refresh token.
   - POST ```/api/v1/auth/admin/register``` - Register a new users [Admin , Doctor , Lab , X-Ray] Role (ADMIN privileges).

### 3. Patient Information:
   - PUT ```/api/v1/{patientId}/patient/info``` - Update the information of a specific patient .(PATIENT , DOCTOR privileges)

### 4. Visit Management:
   - POST ```/api/v1/{doctorId}/doctor/create/visit``` - Create a new visit for a patient under a doctor's supervision. (DOCTOR privileges)
   - PUT ```/api/v1/{doctorId}/doctor/visits/``` - update visit details. (DOCTOR privileges)

### 5. Test Management:
   - POST ```/api/v1/{labId}/lab/{visitId}/tests``` - Create a new test associated with a specific visit in a lab department. (LAB privileges)

### 6. X-Ray Image Management:
   - Post ```/api/v1/{xrayId}/x-ray/{visitId}/x-rays``` - Create a new x-rays associated with a specific visit in an x-ray department.(XRAY privileges)

### 7. Medication Management:
   - POST ```/api/v1/{pharmacistId}/pharmacist/{visitId}/medicines``` - Create a new medicines associated with a specific visit in a pharmacy.
(PHARMACIST privileges)
### 8. User Profile:
   - GET ```/profile/{patientId}``` - Get the profile information of a specific patient.

### 9. Lists of Healthcare Professionals:
   - GET ```/all/doctors``` - Get the list of all doctors.
   - GET ```/all/x-rays``` - Get the list of all x-ray technicians.
   - GET ```/all/pharmacists``` - Get the list of all pharmacists.
   - GET ```/all/labs``` - Get the list of all labs.

### 10. Visit Details:
   - GET ```/api/v1/{doctorId}/doctor/visit-details/{visitId}``` - Get the details of a specific visit under a doctor's supervision. (DOCTOR privileges)
   - GET ```/api/v1/{patientId}/patient/visit-details/{visitId}``` - Get the details of a specific visit for a patient. (PATIENT privileges)


## Data Base
#### SQL Commnad
**Create users for each role**

```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (1, 'admin@example.com', 'John', 'Doe', 'Admin Location', '$2a$10$uMOspXBwi6p9bMERJXdsJ.GhccFDpwGa3s7xMxy/6rMOmZvNwEUcG', '1234567890', 'ADMIN', 'admin');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (2, 'patient@example.com', 'Alice', 'Smith', 'Patient Location', '$2a$10$uMOspXBwi6p9bMERJXdsJ.GhccFDpwGa3s7xMxy/6rMOmZvNwEUcG', '0987654321', 'PATIENT', 'alice');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (3, 'doctor@example.com', 'Michael', 'Johnson', 'Doctor Location', '$2a$10$uMOspXBwi6p9bMERJXdsJ.GhccFDpwGa3s7xMxy/6rMOmZvNwEUcG', '5678901234', 'DOCTOR', 'doctor');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (4, 'pharmacist@example.com', 'Emily', 'Brown', 'Pharmacist Location', '$2a$10$uMOspXBwi6p9bMERJXdsJ.GhccFDpwGa3s7xMxy/6rMOmZvNwEUcG', '4321098765', 'PHARMACIST', 'emily');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (5, 'lab@example.com', 'David', 'Miller', 'Lab Location', '$2a$10$uMOspXBwi6p9bMERJXdsJ.GhccFDpwGa3s7xMxy/6rMOmZvNwEUcG', '6789012345', 'LAB', 'david');
```
```
INSERT INTO _user (id, email, first_name, last_name, location, password, phone, role, username)
VALUES (6, 'xray@example.com', 'Sophia', 'Wilson', 'X-Ray Location', '$2a$10$uMOspXBwi6p9bMERJXdsJ.GhccFDpwGa3s7xMxy/6rMOmZvNwEUcG', '3210987654', 'XRAY', 'sophia');
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
VALUES (2,'FEMALE', '1993-05-19', 160, 60, 'A_NEGATIVE', 'None');
```
```
INSERT INTO visit (description, prescription, summary, treatment, doctor_id, lab_id, medicine_id, patient_id, pharmacist_id, test_id, x_rays_image_id, x_ray_id)
VALUES ('Regular check-up', 123 , 'Visit summary', 'Treatment details', 3, 5, 1, 2, 4, 1, 1, 6);

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

### License
This project is licensed under the MIT License.

