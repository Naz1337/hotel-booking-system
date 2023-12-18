# UMP Hotel Booking System

## Overview
UMP Hotel Booking System is a user-friendly interface designed to streamline the process of booking hotel rooms. It allows users to log in as existing customers, register as new customers, and make room reservations with ease.

## Features
- **Customer Selection**: Users can choose to log in as one of the existing customers or register as a new one.
- **Date Input**: Users can specify the start and end dates of their booking in a DD/MM/YYYY format.
- **Room Availability**: The system displays available rooms based on the inputted booking dates.
- **Room Selection**: Users can select their preferred room from the available options.
- **Payment Method**: Supports payment through cash or card.
- **Receipt Generation**: Generates a receipt upon successful booking and payment, detailing the booking information and total price.

## Usage Instructions

### Starting the Interface
Run the UMP Hotel Booking System. The main interface will prompt you to either log in as an existing customer, register a new customer, or quit the application.

Example:
```
Welcome to UMP Hotel Booking System Interface!
1. Alice Smith
2. Bob Johnson
...
Please choose one of the customer to login as or type 'register' to register a new customer or 'quit' to quit!
```

### Making a Booking
1. **Select a Customer**: Enter the number corresponding to the customer you wish to log in as.
2. **Enter Booking Dates**: Input the start and end dates for your booking in the format DD/MM/YYYY.
3. **Choose a Room**: Select a room from the list of available options based on your booking dates.
4. **Select Payment Method**: Choose your payment method (cash or card).
5. **Complete Payment**: If paying by card, enter your credit card number. The system will generate a receipt.

Example:
```
INPUT: 1
...
Write the name of the room that you would like to book.
INPUT: 2
...
Please enter your credit card number.
INPUT: 4628886137799272
```

### Viewing Receipt
Upon successful payment, a receipt will be displayed, including details such as the room booked, booking duration, total price, and payment method.

### Exiting the System
To exit the system, type 'quit' at the main interface.

## License
MIT lol

---

Thank you for choosing UMP Hotel Booking System!
