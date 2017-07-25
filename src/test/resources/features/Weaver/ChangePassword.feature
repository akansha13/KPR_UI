@Weaver @Mobile @Hybrid @ChangePassword
Feature: Title of your feature

  Background: 
    Given User is logged in app

  @India @ValidateChangePassword @KPR
  Scenario: Title of your scenario
    Given User navigates to Change Password page
    When User enters Old Password, New Password and Retype Password
    Then User validates by Logging in through Changed Password then Resets default Password

  @India @ValidateInvalidChangePassword @KPR
  Scenario Outline: Title of your scenario outline
    Given User navigates to Change Password page
    When User enters invalid/blank Old Password <OldPassword>
    And User enters invalid/blank New Password <NewPassword>
    And User enters invalid/blank Retype Password <RetypePassword>
    Then User validates error messages <errormsg> on Change Password Screen

    Examples: 
      | OldPassword | NewPassword | RetypePassword | errormsg                                        |
      |    12345678 |       12345 |         123456 | * Password should be between 6 to 20 characters |
      |    12345678 |      123456 |          12345 | * Password should be between 6 to 20 characters |
      |    12345678 |      123456 |        1234567 | New and retyped passwords must be same          |
      |             |      123456 |         123456 | Please enter your old password                  |
      |    12345678 |             |         123456 | Please enter your new password                  |
      |    12345678 |      123456 |                | Please retype your new password                 |
