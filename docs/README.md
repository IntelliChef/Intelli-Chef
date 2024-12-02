# IntelliChef Project Documentation

Welcome to the IntelliChef project documentation! This document provides an in-depth overview of the IntelliChef project structure, guidelines, and instructions for team collaboration.

## Project Structure

The IntelliChef project follows a Clean Architecture structure, organized as follows:

```
IntelliChef/
├── docs/                               # Documentation files
│   ├── README.md                       # Overview and setup instructions
│   └── API_Documentation.md            # API-specific integration notes
├── src/                                # Source files
│   ├── main/
│   │   ├── java/
│   │   │   └── com/intelliChef/
│   │   │       ├── entities/           # Core entities (User, Ingredient, etc.)
│   │   │       ├── services/           # Business logic (RecipeSuggestionEngine, etc.)
│   │   │       ├── interface_adapters/           # Adapters for API integrations
│   │   │       └── utils/              # Utility classes (e.g., NutritionalInfo calculations)
│   └── test/                           # Test files
│       └── java/
│           └── com/intelliChef/
│               ├── entities/           # Entity tests
│               ├── services/           # Service tests
│               └── interface_adapters/           # Adapter integration tests
├── .gitignore                          # Ignore unnecessary files
└── README.md                           # app.Main README with project overview and instructions
```

### Directory Descriptions

- **`src/main/java/com/intelliChef/entities/`**: Defines core entities of the system, including `User`, `Ingredient`, `Recipe`, and `Fridge`.
- **`src/main/java/com/intelliChef/services/`**: Contains the business logic of the application, such as the `RecipeSuggestionEngine` for filtering and generating recipe suggestions.
- **`src/main/java/com/intelliChef/interface_adapters/`**: Manages API integrations with Google Gemini and Edamam Recipe APIs.
- **`src/main/java/com/intelliChef/utils/`**: Holds utility classes for calculations, formatting, and shared resources (e.g., `NutritionalInfo`).

### Testing Structure

The `src/test/java/com/intelliChef/` folder mirrors the main source structure to provide testing coverage for each component. For example:
- **`entities/`**: Unit tests for core entities like `User` and `Recipe`.
- **`services/`**: Tests for verifying the logic in the `RecipeSuggestionEngine`.
- **`interface_adapters/`**: Tests for API integration and response handling.

## Development Guidelines

1. **Coding Standards**: Follow Java best practices for readability and maintainability.
2. **Code Reviews**: Peer code reviews are mandatory to ensure quality and adherence to project guidelines.
3. **Documentation**: Each function/method should include a brief comment explaining its purpose.

## Additional Resources

For detailed information on integrating with external APIs, refer to [API_Documentation.md](API_Documentation.md).
