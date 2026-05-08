Employee Payroll App

Goal: Develop an Employee Payroll Management System that demonstrates Object-Oriented Programming concepts in real-world enterprise application development. Description: The app shows how encapsulation, inheritance, composition, polymorphism, and exception handling solve business problems while ensuring scalability and maintainability.

UC1: Employee Registration
Goal: Register a new employee with validated personal and salary information. Description: Uses encapsulation, constructor overloading, and RegEx validation to ensure data integrity. Combines Employee and UserAccount objects for secure credential storage and returns confirmation.

UC2: Employee Authentication & Login
Goal: Securely authenticate and grant dashboard access. Description: Implements inheritance and polymorphism for role-based access. Uses password hashing, session management, and abstract classes to provide secure authentication and extensible user hierarchy.

UC3: Payslip Generation
Goal: Generate detailed monthly payslip breakdown. Description: Applies composition and aggregation to model Employee and SalaryComponents. Uses PayrollService for salary calculations, applies deductions, and formats payslip output for professional display.

UC4: Payslip Print / Download
Goal: Generate downloadable payslip copy. Description: Demonstrates cloning, immutability, and equals/hashCode contracts. Ensures original data integrity, supports multiple file formats, and enables version control through proper file naming.

UC5: Dashboard Display
Goal: Display personalized payroll dashboard. Description: Implements Dashboard interface with runtime type checking and Stream API processing. Provides YTD summaries, recent payslips, and supports multiple dashboard types with extensible analytics.

UC6: Input Validation
Goal: Validate all user inputs robustly. Description: Centralizes validation logic in ValidationService. Uses custom exception hierarchy, RegEx pattern matching, and sanitization to enforce fail-fast validation with user-friendly error messages.
