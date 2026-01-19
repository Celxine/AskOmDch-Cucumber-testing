# AskOmDch-Cucumber-testing
## 📌 Project Overview
This project is an automated testing framework for the **AskOmDch E-Commerce Website** (https://askomdch.com). It utilizes **Selenium WebDriver**, **Java**, and **Cucumber BDD** to validate critical business flows including User Management, Shopping Cart, and Checkout processes.

## 🛠 Tech Stack
* **Language:** Java 17
* **Framework:** Cucumber (BDD)
* **Web Driver:** Selenium 4
* **Build Tool:** Maven
* **Reporting:** Allure & Maven Cucumber Reporting
## 🧪 Test Strategy & Success Criteria

To ensure robust quality assurance, we moved beyond "happy path" testing. We defined strict acceptance criteria and specific "Attack Vectors" (edge cases) to validate system stability.

### 1. Standard Test Persona ("Cole")
All baseline positive tests use this standardized persona to ensure consistency.

| Field | Value | Notes |
| :--- | :--- | :--- |
| **First Name** | `Cole` | Standard string |
| **Last Name** | `Train` | Standard string |
| **Email** | `cole.train@example.com` | Standard format |
| **Address** | `123 Main St.` | US Format |
| **Zip Code** | `90210` | US Format (California) |
| **Payment** | `Cash on Delivery` | Primary test method to avoid gateway friction |

### 2. Edge Case Matrix (The "Attack Plan")
We explicitly test these boundary conditions. If the system accepts "Invalid Data" without an error message, it is marked as a **FAIL**.

| Feature            | Test Scenarios (Edge Cases) | Success Criteria (Expected Result) |
|:-------------------| :--- | :--- |
| **Checkout Forms** | **Empty Fields:** Submit form with blank required fields.<br>**Max Length:** Enter 200+ characters in "First Name".<br>**Script Injection:** Enter `<script>` tags in "Order Notes". | System displays strict error: *"Field is required"* or *"Character limit exceeded"*. Database rejects code injection. |
| **Coupons**        | **Expired:** Enter an old coupon code.<br>**Invalid:** Enter `GHOST_CODE_123`. | System displays red error banner: *"Coupon does not exist."* Cart total remains unchanged. |
| **Cart Logic**     | **Zero Qty:** Update product quantity to `0`.<br>**Negative Qty:** Update product quantity to `-1`. | Product is removed from cart (Zero) or system rejects input (Negative). |
| **Searchc**        | **No Results:** Search for `SpaceX Rocket`.<br>**Special Chars:** Search for `%%%`. | Display *"No products found"* message. App does not crash. |

### 3. Defect Reporting Policy
Since we are using Cucumber BDD, defects are handled as follows:
1.  **Strict Fail:** If actual result != Expected Result, the test is marked **FAILED**.
2.  **Reporting:** Bugs are logged with steps to reproduce (e.g., *"System allows 5000 characters in Name field, causing UI breakage"*).
3.  **Tags:** Known bugs in the automation suite are tagged `@Bug` or `@WIP` to distinguish them from regression failures.

---