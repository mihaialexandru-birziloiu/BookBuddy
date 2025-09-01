# BookBuddy

BookBuddy is an AI-powered application that provides personalized book recommendations based on user input. It leverages OpenAI's GPT-4.1-nano model for generating recommendations and the text-embedding-3-small model for embeddings.

## Features

- **AI-Powered Recommendations**: Uses OpenAI's GPT-4.1-nano model to generate book suggestions.
- **Interactive Frontend**: A modern React-based user interface for seamless interaction.
- **Backend API**: A Spring Boot backend to handle requests and integrate with the AI model.

## Technologies Used

- **Frontend**: React, TypeScript, Vite
- **Backend**: Java, Spring Boot
- **AI Models**: OpenAI GPT-4.1-nano, text-embedding-3-small
- **Build Tools**: Maven

## Prerequisites

To run the project locally, ensure you have the following installed:

- Node.js (v18 or later)
- Java Development Kit (JDK 17 or later)
- Maven

## Environment Variables

The application requires the following environment variable to function:

- `OPENAI_API_KEY`: Your OpenAI API key for accessing the GPT model.

### Setting Up Environment Variables

#### On Windows (PowerShell):

```powershell
$env:OPENAI_API_KEY="your-api-key"
```

#### Using a `.env` File:

Create a `.env` file in the root directory and add:

```
OPENAI_API_KEY=your-api-key
```

## How to Run Locally

### 1. Clone the Repository

```bash
git clone https://github.com/mihaialexandru-birziloiu/BookBuddy.git
cd BookBuddy
```

### 2. Build the Backend

```powershell
.\mvnw clean package 
```

The `.jar` file will be generated in the `target` directory.

### 3. Start the Backend

```powershell
java -jar target/BookBuddy-0.0.1-SNAPSHOT.jar
```

### 4. Start the Frontend

Navigate to the `client` directory:

```powershell
cd client
npm install
npm run dev
```

### 5. Access the Application

- Frontend: [http://localhost:3000](http://localhost:3000)
- Backend: [http://localhost:8080](http://localhost:8080)

## Project Structure

```
BookBuddy/
├── client/                # Frontend code
│   ├── src/
│   ├── public/
│   ├── package.json
├── src/                   # Backend code
│   ├── main/java/dev/alex/BookBuddy/
│   ├── main/resources/
│   └── test/java/dev/alex/BookBuddy/
├── README.md              # Project documentation
```

## Notes

- Ensure the `OPENAI_API_KEY` is set before running the application.
- The application uses OpenAI's GPT-4.1-nano for recommendations and text-embedding-3-small for embeddings.
