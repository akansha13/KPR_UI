@Weaver @Mobile @Hybrid @Login
Feature: This is login feature

  @India @ValidateLogin @KPR
  Scenario: Testing with valid credentials
    Given User navigates to login popup
    When User enters valid app credentials in app
    Then User validates avatar icon is visible in app

  @India @ValidateInvalidLogin @KPR
  Scenario Outline: Testing with invalid credentials
    Given User navigates to login popup
    When User enters invalid Username <Username>
    And User enters invalid Password <Password>
    Then User validates error message <errormsg> visible in app

    Examples: 
      | Username    | Password | errormsg                                        |
      | nicenikhil  | 12345678 | Either username or password is invalid          |
      | nicenikhil1 |   123456 | Either username or password is invalid          |
      | nicenikhil1 |          | * Please enter Password                         |
      |             | 12345678 | * Please enter Username                         |
      |             |          | * Please enter Username * Please enter Password |
