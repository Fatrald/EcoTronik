# Cloud Computing Path

As cloud engineers, we play a role in developing APIs with the concept of CRUD (Create, Read, Update, and Delete) using the Javascript programming language with the express framework. As a cloud engineers we have job descriptions including:

- Create endpoints for register, login, logout, transactions, and electronics
- Deploy Backend API on Google Cloud Platform by using App Engine with the app.yaml as supporting file
- Deploying Machine Learning models on Google Cloud Platform by using Cloud Run with Docker as supporting files
- Create SQL Instance for database and connect to it using private IP Address
- Creating Cloud Storage (bucket) to store electronic datasets

# API Endpoint

ENDPOINT |    METHOD    | Body Sent (JSON) | Description,
:-------:|:------:|:----------------:|:----------:|
/        |GET     |None              |Testing Endpoint & Server|
/login   |POST & GET|Username & Password|Authentication for user|
/users   |POST    |Nama, Email & Password|Registration for user|
/users   |PATCH   |Nama, Alamat, Email, profile_image & no.telp | Update data for user|
/logout  |DELETE  | None | Logout four user|
/elektronik/:id |GET | None | Give the response about elektronik use id elektronik|
