# arielkiell.wixsite
A Selenium automation test using the Page Object Model (POM) to simulate adding products to the cart, verifying pricing, and taking screenshots on the Ariel Kiell shop website.

# Selenium POM Shopping Cart Test

This repository contains an automated test written in Java using Selenium WebDriver, following the Page Object Model (POM) design pattern. The test automates the following actions on the Ariel Kiell shop website:

1. Open the website [Ariel Kiell Shop](https://arielkiell.wixsite.com/interview)
2. Navigate to the shop section
3. Select the best-seller product
4. Choose a product color
5. Add three items to the cart using the quantity up arrow
6. View the cart and proceed to checkout
7. Verify the total price equals 54 CAD
8. Take screenshots of each step for documentation

## Tools and Technologies
- **Java**: Programming language used for the test.
- **Selenium WebDriver**: For browser automation.
- **TestNG**: For managing test cases and assertions.
- **Maven**: For project build and dependency management.
- **Page Object Model (POM)**: Design pattern used for better test structure.
- **WebDriverManager**: This is used to manage the browser drivers automatically.
- **Screenshot Utility**: Captures screenshots at each significant step.

## Getting Started

### Prerequisites
1. Java 8 or higher installed on your system.
2. Maven installed and configured.
3. A browser (e.g., Chrome, Firefox) for testing.
4. Git to clone the repository.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/rrefaat/arielkiell.wixsite.git
   cd arielkiell.wixsite
