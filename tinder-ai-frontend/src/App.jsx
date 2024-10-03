// src/App.jsx
import "./App.css";
import React, { useState, useEffect } from "react";
import { User, MessageCircle } from "lucide-react";
import { fetchRandomProfile, saveSwipe, fetchMatches, fetchConversation } from "./utils/api";
import ProfileSelector from "./components/ProfileSelector";
import MatchesList from "./components/MatchesList";
import ChatScreen from "./components/ChatScreen";

function App() {
  const loadRandomProfile = async () => {
    try {
      const profile = await fetchRandomProfile();
      setCurrentProfile(profile);
    } catch (error) {
      console.error(error);
    }
  };

  const loadMatches = async () => {
    try {
      const matches = await fetchMatches();
      setMatches(matches);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadRandomProfile();
    loadMatches();
  }, []);

  const [currentScreen, setCurrentScreen] = useState("profile");
  const [currentProfile, setCurrentProfile] = useState(null);
  const [matches, setMatches] = useState([]);
  const [currentMatchAndConversation, setCurrentMatchAndConversation] = useState({ match: {}, conversation: [] });

  const onSwipe = async (profileId, direction) => {
    loadRandomProfile();
    if (direction === 'right') {
      await saveSwipe(profileId);
      await loadMatches();
    }
  };

  const onSelectMatch = async (profile, conversationId) => {
    const conversation = await fetchConversation(conversationId);
    setCurrentMatchAndConversation({ match: profile, conversation: conversation });
    setCurrentScreen("chat");
  };

  const refreshChatState = async () => {
    const conversation = await fetchConversation(currentMatchAndConversation.conversation.id);
    setCurrentMatchAndConversation({ match: currentMatchAndConversation.match, conversation: conversation });
  };

  const renderScreen = () => {
    switch (currentScreen) {
      case "profile":
        return <ProfileSelector profile={currentProfile} onSwipe={onSwipe} />;
      case "matches":
        return <MatchesList matches={matches} onSelectMatch={onSelectMatch} />;
      case "chat":
        return <ChatScreen currentMatch={currentMatchAndConversation.match} conversation={currentMatchAndConversation.conversation} refreshState={refreshChatState} />;
      default:
        return null;
    }
  };

  return (
      <div className="max-w-md mx-auto p-4">
        <nav className="flex justify-between mb-4">
          <User onClick={() => setCurrentScreen("profile")} />
          <MessageCircle onClick={() => setCurrentScreen("matches")} />
        </nav>
        {renderScreen()}
      </div>
  );
}

export default App;
