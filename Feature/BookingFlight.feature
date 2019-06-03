Feature: Clear Trip Booking
  I want to book flight on clear trip website

  Background: User logs in
    Given User logins into ClearTrip

  Scenario Outline: Flight Booking
    Given User books a flight "<source>" "<destination>"

    Examples: 
      | source    | destination |
      | Bangalore | Delhi       |
