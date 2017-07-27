@Weaver @Mobile @Hybrid @Registration
Feature: This is Registration feature

  @India @ValidateRegistration @KPR
  Scenario: Testing with valid Registration details
    Given User navigates to Registration page
    When User enters valid Registration details and sets Nickname
    Then User validates Lobby is visible in App

  @India @ValidateInvalidRegistration @KPR
  Scenario Outline: Testing with invalid/blank Registration details
    Given User navigates to Registration page
    When User enters invalid/blank Username <Username>
    And User enters invalid/blank Password <Password>
    And User enters invalid/blank Email Address <EmailAddress>
    And User enters invalid/blank Mobile Number <MobileNumber>
    Then User validates error messages <errormsg> on Registration Screen

    Examples: 
      | Username | Password | EmailAddress      | MobileNumber | errormsg                                        |
      | nice     |   123456 | nice@skilrock.com |   9012345678 | * Username should be 5 to 21 characters         |
      | nice1    |    12345 | nice@skilrock.com |   9012345678 | * Password should be between 6 to 20 characters |
      | nice1    |   123456 | nice@skilrock     |   9012345678 | * Please enter valid Email Address              |
      | nice1    |   123456 | nice@skilrock.com |    901234567 | * Enter 10 Digit No. without 0/+91              |
      |   123456 |   123456 | nice@skilrock.com |   9012345678 | * The first character should be an alphabet     |
      |          |   123456 | nice@skilrock.com |   9012345678 | * Please enter Username                         |
      | nice1    |          | nice@skilrock.com |   9012345678 | * Please enter Password                         |
      | nice1    |   123456 |                   |   9012345678 | * Please enter valid Email Address              |
      | nice1    |   123456 | nice@skilrock.com |              | * Please enter valid 10 digit mobile number     |
