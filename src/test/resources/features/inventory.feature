Feature: Product Inventory

  @regression
  Scenario: Sort products by name descending
    Given the user is logged into SauceDemo as a standard user
    When the user sorts products by nameDesc
    Then the first product should be redTshirt

  @regression
  Scenario: Sort products by price ascending
    Given the user is logged into SauceDemo as a standard user
    When the user sorts products by priceAsc
    Then the first product should be onesie

  @regression
  Scenario: Cart badge counts added products
    Given the user is logged into SauceDemo as a standard user
    When the user adds backpack to the shopping cart
    And the user adds bikeLight to the shopping cart
    Then the cart badge should show 2 items
