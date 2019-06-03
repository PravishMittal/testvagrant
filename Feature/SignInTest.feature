Feature: Clear Trip Booking
  I want to Sign In on clear trip website

  Background: User logs in
    Given User logins into ClearTrip

  Scenario Outline: SignIn Test
    Given User checks the trip "<ErrorMessage>"
    
    Examples:
    |ErrorMessage|
    |There were errors in your submission|
