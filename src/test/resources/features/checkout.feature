Feature: End-to-End Checkout

  Scenario: Successfully ordering a Sauce Labs Onesie
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    Then the cart should contain 1 item of "Sauce Labs Onesie"
    When the user proceeds to checkout
    And fills in customer information with first name "John", last name "Doe", and postal code "12345"
    And continues to the checkout overview
    Then the order summary should show "Sauce Labs Onesie" with quantity 1
    When the user finishes the checkout
    Then the order confirmation message "Thank you for your order!" should be displayed
