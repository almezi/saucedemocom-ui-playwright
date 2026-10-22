Feature: User Authentication

  @sanity @regression
  Scenario: Successful login with standard credentials
    Given the user is on the SauceDemo login page
    When the user logs in with valid standard credentials
    Then the user should be directed to the inventory page
    And the products catalog header should be visible

  @regression
  Scenario: Login failure with locked out user
    Given the user is on the SauceDemo login page
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then an error message stating "Sorry, this user has been locked out." should be displayed
