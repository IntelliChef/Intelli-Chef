# IntelliChef

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technical Requirements](#technical-requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [Testing](#testing)
- [Support](#support)
- [License](#license)

## Overview
IntelliChef is a desktop application designed to help young adults discover recipes based on available ingredients while considering nutritional goals. The application addresses the challenge of finding suitable recipes that not only utilize ingredients on hand but also align with specific dietary requirements - a common difficulty faced by individuals new to cooking.

### Contributors
- Naren
- Harpuneet
- Enza
- Fatir

## Features
1. **Ingredient Input**
    - Upload images of available ingredients
    - Manual ingredient entry option

2. **Dietary Preferences**
    - Set nutritional goals
    - Specify dietary restrictions

3. **Recipe Discovery**
    - Browse recipes filtered by available ingredients
    - Recipes automatically aligned with nutritional preferences

4. **Detailed Recipe View**
    - Enhanced recipe visualization
    - Direct links to cooking instructions

## Technical Requirements
- Operating System: Desktop platforms supporting Java
- Java Version: OpenJDK 22.0.2 or later
- Build System: Maven

### Dependencies
- org.json:json:20240303
- com.google.cloud:google-cloud-vertexai
- com.formdev:flatlaf:3.0
- JUnit and Mockito (for development)

## Installation
1. Clone the repository
   ```bash
   git clone [repository-url]
   ```
2. Ensure OpenJDK 22.0.2 or later is installed
3. Build the project using Maven
   ```bash
   mvn clean install
   ```
4. Run the application
   ```bash
   mvn exec:java
    ```

## Usage
Uploading the Image:

https://github.com/user-attachments/assets/913f5e9f-dfe7-4a51-9a15-93b6600bafc3

Scan is taking place: 

![Screenshot 2024-12-01 012108](https://github.com/user-attachments/assets/8fdc4c45-8fb7-4f74-9df5-c315de9cfcd4)

No ingredients found in given picture: 

![Screenshot 2024-12-01 013036](https://github.com/user-attachments/assets/27f7d6f4-0586-4aee-80fc-9b46b85f9f3c)

Error Screen:

![Screenshot 2024-12-01 012750](https://github.com/user-attachments/assets/bfdcc400-2716-4b21-a173-2d1bd73981eb)

Ingredients Detected:

![Screenshot 2024-12-01 013455](https://github.com/user-attachments/assets/4dfa67e3-3d6a-44a1-bb6b-d3a4d2778f03)


## Contributing
We welcome contributions to IntelliChef. Please follow these guidelines:

1. Fork the repository
2. Create a feature branch
3. Implement your changes following:

   - Clean Architecture principles
   - SOLID design principles

4. Ensure all tests pass
5. Submit a pull request from your branch

## Development Setup
Follow the installation instructions above to set up your development environment. The project uses Maven for dependency management and building.

## Testing
The project employs JaCoCo for test coverage analysis. Run tests using:
   ```bash
    mvn test jacoco:report
   ```
## Support
For bug reports, please create a new issue
For discussions and questions, use GitHub's discussion tools

## License
This project is licensed under the GNU GPL v3.0 - see the LICENSE file for details.
