# Tinder AI

Welcome to the **Tinder AI** app development project! This project showcases a full-stack dating application that combines the familiar swipe-and-match mechanics of dating apps with advanced AI capabilities. You'll experience the integration of AI models to generate dynamic user profiles, images, and conversations.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Dependencies](#dependencies)
- [Contributors](#contributors)
- [License](#license)

## Introduction

This project demonstrates how to build a dating app using **Spring Boot** for the backend, **React** for the frontend, and powerful AI models like **GPT-4** / **ollama** and **Stable Diffusion** for content generation. The Tinder AI app showcases:

- RESTful API development with **Spring Boot**.
- **MongoDB** for efficient NoSQL database management.
- AI-generated profiles and AI-powered conversations using GPT-4.
- Image generation with **Stable Diffusion**.
- Interactive user experience with a **React** single-page application (SPA).

## Features

- **AI-Generated Profiles**: Fictional user profiles generated with GPT-4.
- **AI-Powered Conversations**: Users can interact with AI-driven chatbots based on generated profiles.
- **Dynamic Content Generation**: Profile pictures created using Stable Diffusion.
- **Full-Stack Architecture**: Backend API development with Spring Boot and MongoDB, and a responsive frontend built with React.

## Installation

### Step 1: Clone the Repositories

To get started, clone both the frontend and backend repositories:

```bash
git clone https://github.com/koushikkothagal/tinder-ai-backend
git clone https://github.com/koushikkothagal/tinder-ai-frontend
```

### Step 2: Set Up the Profile Data

1. Download the profile bundle from the provided links (choose either men or women profiles).
2. Extract the profile bundle.
3. Copy the `profile.json` file to the root directory of the **tinder-ai-backend** project (alongside files like `README.md` or `pom.xml`).

### Step 3: Set Up the Images

Copy all images from the downloaded profile bundle into the `resources/static/images` directory of the **tinder-ai-backend** project.

### Step 4: Update User Details

In the `resources/application.properties` file, update the user details under the following key:

```properties
tinderai.character.user={id:'user', ... }
```

Leave the id field as is, and modify the other details according to your own profile.

### Step 5: Configure AI Key

#### Option 1: Configure OpenAI API Key

Set your OpenAI API key in the environment variable `SPRING_AI_OPEN_AI_API_KEY`. You can generate an API key from the [OpenAI platform](https://platform.openai.com) if you don’t have one.

#### Option 2: Use Ollama as an OpenAI Alternative

If you don't have an OpenAI API key due to its cost, you can use **Ollama** as an alternative. Ollama is a local AI model runner that supports various AI models, such as **Mistral**.

#### Ollama Configuration

To use Ollama instead of OpenAI, add the following configurations to the `application.properties` file:

```properties
# Configure the Ollama chat model:
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.options.model=mistral
spring.ai.ollama.chat.options.temperature=0.7
```

### Install Ollama Locally

You can install and run **Ollama** locally by following these steps:

1. **Install Ollama**: Visit the [Ollama website](https://ollama.com) to download the installation package for your operating system.
2. **Run Ollama**: After installation, start the Ollama service with the following command:

    ```bash
    ollama run mistral
    ```

    Ensure that Ollama is running on `localhost:11434` as specified in the configuration.

## Terminal Screen for Ollama 
   <img width="1016" alt="image" src="https://github.com/user-attachments/assets/d6948dab-8551-494f-a1f3-f8472a8cf1e2">


### Step 6: Start the Servers

1. Start the backend server by running the Spring Boot application.
2. Start the frontend server by running the React app.
3. Visit the local URL for the React app to start using the Tinder AI app.

### Useful Resources

- [Java Brains Tutorials: Java Backend Learning Path](https://javabrains.io)
- [Official Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Official React Documentation](https://reactjs.org)
- [MongoDB Documentation](https://docs.mongodb.com)
- [Ollama Documentation](https://ollama.com)

## Snaps:

### Profile Selector Screen
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/4ab4e1e2-0a5d-4c4f-a36b-0de8c7c31ffc">

### User's Matches List Screen
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/748f728f-d8b6-4549-8aea-ba1767bd7068">

### Chat Screen from selected Match List
<img width="1440" alt="image" src="https://github.com/user-attachments/assets/37f1bab9-5ea4-4d63-b274-f89e2af80775">


