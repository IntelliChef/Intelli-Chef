# IntelliChef

IntelliChef is a Nutritionally Conscious Recipe Generator that allows users to find recipe suggestions based on their available ingredients, dietary preferences, kitchen expertise, and appetite size. Utilizing the Google Gemini API for image recognition and the Edamam Recipe API, IntelliChef offers personalized, nutritionally detailed recipes to help users make informed dietary choices.

## Features
- **Ingredient Recognition**: Upload an image of your fridge contents to detect available ingredients.
- **Recipe Suggestions**: Generate recipes based on dietary preferences, cuisine types, and skill level.
- **Nutritional Breakdown**: View caloric, macro, and micronutrient information for each recipe.

## Technologies Used
- Java
- Maven
- Google Gemini API (for image recognition)
- Edamam Recipe API (for recipe generation)

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/IntelliChef.git
```

### 2. Install Dependencies
The project uses Maven for dependency management. Run the following command to install dependencies:

```bash
mvn clean install
```

### 3. Set Up API Keys
- Google Gemini API and Edamam Recipe API keys are required.
- Refer to the API_Documentation.md for setup instructions on obtaining and using API keys.

### 4. Run the Application
```bash
mvn exec:java -Dexec.mainClass="com.intelliChef.Main"
```

## Project Structure
Refer to the docs for a full breakdown of IntelliChef's project structure.

## Team Members
- Enkhzaya
- Fatir
- Naren
- Harpuneet

Happy coding!
