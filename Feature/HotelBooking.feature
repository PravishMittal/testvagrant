Feature: Clear Trip Booking
  I want to book hotel on clear trip website

  Background: User logs in
    Given User logins into ClearTrip

  Scenario Outline: Hotel Booking
    Given User books a hotel "<Locality>" "<Travelllers>"

    Examples: 
      | Locality               | Travelllers      |
      | Indiranagar, Bangalore | 1 room, 2 adults |
