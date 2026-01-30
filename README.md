# ATM Simulator (Java & MySQL)

A desktop-based ATM Simulator application built using **Java (Swing)** and **MySQL**, designed to simulate real-world banking operations such as authentication, balance enquiry, deposits, withdrawals, and transaction history.

## Features
- Secure user login with PIN authentication
- Balance enquiry
- Cash deposit and withdrawal
- Mini statement / transaction history
- PIN change functionality
- Persistent data storage using MySQL

## Tech Stack
- Java (Swing)
- MySQL
- JDBC

## Database Setup

Run the following command to set up the database:

```bash
mysql -u root -p bank_management_system < database/bank_setup.sql

