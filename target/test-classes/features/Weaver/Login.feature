@Weaver @Mobile @Hybrid @Login
Feature: This is login feature

  @India @ValidateLogin @KPR
  Scenario: Testing with valid credentials
    Given User navigates to login popup
    When User enters valid app credentials are entered in app
    Then User validates Lobby is visible in app

  @India @ValidateInvalidLogin @KPR
  Scenario Outline: Testing with invalid credentials
    Given User navigates to login popup
    When User enters invalid Username <Username>
    And User enters invalid Password <Password>
    Then User validates Lobby is not visible in app

    Examples: 
      | Username    | Password |
      | nicenikhil  | 12345678 |
      | nicenikhil1 |   123456 |
      | nicenikhil1 |          |
      |             | 12345678 |
      |             |          |
