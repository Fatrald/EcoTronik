# Cloud Computing Path

As cloud engineers, we play a role in developing APIs with the concept of CRUD (Create, Read, Update, and Delete) using the Javascript programming language with the express framework. 

## Cloud Computing Job Description
As a cloud engineers we have job descriptions including:

- Create endpoints for register, login, logout, transactions, and electronics
- Deploy Backend API on Google Cloud Platform by using App Engine with the app.yaml as supporting file
- Deploying Machine Learning models on Google Cloud Platform by using Cloud Run with Docker as supporting files
- Create SQL Instance for database and connect to it using private IP Address
- Creating Cloud Storage (bucket) to store electronic datasets

## API Endpoint

ENDPOINT |    METHOD    | Body Sent (JSON) | Description,
:-------:|:------:|:----------------:|:----------:|
/        |GET     |None              |Testing Endpoint & Server|
/login   |POST & GET|Username & Password|Authentication for user|
/users   |POST    |Nama, Email & Password|Registration for user|
/users   |PATCH   |Nama, Alamat, Email, profile_image & no.telp | Update data for user|
/logout  |DELETE  | None | Logout four user|
/elektronik/:id |GET | None | Give the response about elektronik use id elektronik|

## How to Deploy API Backend using App Engine (GCP)
1. Push the Backend API that you have created into your GitHub repository.
2. Clone the GitHub repository which contains your Backend API in the terminal with the statement `git clone <your repository link>` then click enter.
3. Affter success, run the `gcloud app deploy` statement to start the deployment process.
4. After the deployment process is complete, you can run the `gcloud app browse` statement to get the link from your deployment results earlier.

## How to Deploy Datasets and Model with flask using Cloud Run and Cloud Storage (GCP)
1. Upload the datasets that you have created into Google Cloud Storage (Bucket).
2. Create Flask API and deployed it on Google Cloud run.
3. Upload the model using Docker File into Container Registery .
4. Deployed it in Cloud Run, Create service and fill all of the conditions and adjust the port with the port in the docker file.
   
