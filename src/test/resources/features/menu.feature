Feature: Application Menu

  @regression
  Scenario: Logout returns to the login page
    Given the user is logged into SauceDemo as a standard user
    When the user logs out
    Then the user should be on the SauceDemo login page

  @regression
  Scenario: Reset app state clears the cart
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And the user resets the app state
    Then the cart badge should not be visible
