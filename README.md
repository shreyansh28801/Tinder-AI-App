# Tinder AI App

Welcome to the **Tinder AI App** – a full-stack dating application that combines Spring Boot and React with cutting-edge AI services like GPT-4 and Stable Diffusion to create AI-generated profiles and AI-powered conversations!

## Project Overview

This project is designed to simulate the popular swipe-and-match dating app, but with a twist: instead of real users, the app generates fictional profiles using GPT-4 and their corresponding pictures using Stable Diffusion. When users match, they can engage in conversations with AI-driven chatbots, each possessing a unique personality.

### Key Features:
- **Spring Boot Backend:** RESTful API with AI integrations.
- **MongoDB Integration:** NoSQL database for managing profiles.
- **React Frontend:** Single-page application with state management using React hooks.
- **AI-Generated Profiles:** GPT-4 generates diverse fictional profiles.
- **AI-Powered Conversations:** Chatbots powered by GPT-4 based on the generated profiles.
- **Dynamic Content Generation:** Profile pictures created using Stable Diffusion.

## Getting Started

### Prerequisites:
- **Git** for version control
- **Java 11+** for backend development
- **Node.js and npm** for frontend development
- **MongoDB** for data storage
- **OpenAI API key** for GPT-4 integration
- **Stable Diffusion model** for image generation

### Step 1: Clone the Repositories
Clone the frontend and backend repositories:
```bash
git clone https://github.com/koushikkothagal/tinder-ai-backend
git clone https://github.com/koushikkothagal/tinder-ai-frontend
```

## Step 2: Set Up the Profile Data
- Download the profile bundle from the provided links (choose either men or women profiles).
- Extract the profile bundle.
- Copy the `profile.json` file to the root directory of the `tinder-ai-backend` project (alongside files like `README.md` or `pom.xml`).

## Step 3: Set Up the Images
- Copy all images from the downloaded profile bundle into the `resources/static/images` directory of the `tinder-ai-backend` project.

## Step 4: Update User Details
- In the `resources/application.properties` file, update the user details under:
  ```properties
  tinderai.character.user={id:'__', ... }


- Leave the `id` field as is, and modify the other details according to your own profile.

## Step 5: Configure OpenAI API Key
- Set your OpenAI API key in the environment variable `SPRING_AI_OPEN_AI_API_KEY`. You can generate an API key from the OpenAI platform if you don’t have one.

## Step 6: Start the Servers
- Start the backend server by running the Spring Boot application.
- Start the frontend server by running the React app.
- Visit the local URL for the React app to start using the Tinder AI app.

## Useful Resources
- [Java Brains Tutorials: Java Backend Learning Path](https://www.javabrains.io/pages/complete-java-backend-learning-path)
- [Official Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Official React Documentation](https://reactjs.org/)
- [MongoDB Documentation](https://www.mongodb.com/)

## License
This project is licensed under the MIT License.
