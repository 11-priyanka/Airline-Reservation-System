# Airplane-Reservation-System

## Introduction
This project is a RESTful Airline Reservation System built using Java and Spring Boot. It allows users to search for available flights, check fare details, book flight reservations, apply discount coupons and confirm bookings.

## Technologies Used
* **Language:** Java
* **Framework:** Spring Boot
* **Database:** In-Memory H2 Database (Initialized using DataInitializer class)
* **Tools:** Postman (for API testing)

## Restrictions Applicable for the Codebase
* **Database Limitation:** The system uses an in-memory H2 database, meaning all data is lost when the application restarts.
* **Data Initialization:** Data is initialized using the DataInitializer class, not data.sql.
* **No Authentication:** The application does not implement authentication or authorization.
* **No Frontend:** This is a backend-only project. It provides REST APIs but does not include a user interface.
* **No Seat Selection:** The reservation system does not allow seat selection or modifications after booking.

## Features & Services
* **Retrieve All Available Flights** – View all available flights (excluding fare).
* **Get Flight Fare Details** – Retrieve fare for a specific flight.
* **Create a Reservation** – Book a flight for a passenger.
* **Apply a Discount Coupon** – Apply a discount to an existing reservation.
* **Confirm a Reservation** – Finalize a reservation by confirming it.
* **Retrieve All Coupons** – View all available discount coupons.

## API Endpoints & Their Functions
1. **Get All Available Flights :**
   `Endpoint: GET /flights`
   Description: Returns a list of available flights without fare details.
2. **Get Fare Details of a Flight :**
   `Endpoint: GET /flights/fare/{flightId}`
   Description: Returns fare details for a specific flight based on its flightId.
3. **Create a Flight Reservation :**
   `Endpoint: POST /reservations/create`
   _Request Body:_ 
   `{
   "passengerName": "John Doe",
   "email": "johndoe@example.com",
   "flightId": 1
   }`
   _Response:_ Returns reservation details with "status": "Not Confirmed Yet".
4. **Get All Available Coupons :**
   `Endpoint: GET /coupons/apply`
   Description: Returns a list of all available discount coupons.
4. **Apply a Discount Coupon :**
   `Endpoint: PUT /coupons/apply`
    _Request Body:_
    `{
     "reservationId": 1,
     "couponCode": "DISCOUNT50"
     }`
     _Response:_ Returns updated reservation with the applied discounted fare.
6. **Confirm a Reservation :**
`   Endpoint: PUT /reservations/confirm/{reservationId}
`   Description: Changes reservation status from "Not Confirmed Yet" to "Confirmed".

## Testing Procedure
* Run the Spring Boot application.
* Use Postman to send API requests to test various functionalities.
* Verify responses to ensure correct functionality.

## Sample Testing Data
### Sample Flights
`[
{
"flightId": 1,
"airline": "Air India",
"origin": "Delhi",
"destination": "Mumbai",
"departureTime": "2025-02-22T08:30:00",
"arrivalTime": "2025-02-22T10:30:00"
}
]`


### Sample Coupons
`[
{
"id": 1,
"code": "DISCOUNT10",
"discount": 500.00
}
]`

### Sample Reservation Request
`{
"passengerName": "Priyanka Nigam",
"email": "priyanka@example.com",
"flightId": 1
}`

## Error Handling Scenarios
The application handles various error cases by returning meaningful messages.

* **Invalid Flight ID:** If a user provides a flightId that does not exist.
* **Invalid Reservation ID:** If a user tries to confirm or apply a coupon to a non-existent reservation.
* **Invalid Coupon Code:** If a user applies a coupon that does not exist.
* **Reservation Already Confirmed:** If a user attempts to confirm a reservation that has already been confirmed.

