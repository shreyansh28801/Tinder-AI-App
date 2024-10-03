// src/components/ChatScreen.jsx
import React, { useState } from "react";
import { User, Send } from "lucide-react";
import { sendMessage } from "../utils/api";

const ChatScreen = ({ currentMatch, conversation, refreshState }) => {
    const [input, setInput] = useState("");

    const handleKeyPress = (e) => {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            handleSend(conversation, input);
        }
    };

    const handleSend = async (conversation, input) => {
        if (input.trim()) {
            await sendMessage(conversation.id, input);
            setInput("");
        }
        refreshState();
    };

    return currentMatch ? (
        <div className="rounded-lg shadow-lg p-4">
            <h2 className="text-2xl font-bold mb-4">Chat with {currentMatch.firstName} {currentMatch.lastName}</h2>
            <div className="h-[50vh] border rounded-lg overflow-y-auto mb-6 p-4 bg-gray-50">
                {conversation.messages.map((message, index) => (
                    <div key={index} className={`flex ${message.authorId === 'user' ? 'justify-end' : 'justify-start'} mb-4`}>
                        <div className={`flex items-end ${message.authorId === 'user' ? 'flex-row-reverse' : 'flex-row'}`}>
                            {message.authorId === 'user' ? (
                                <User size={15} />
                            ) : (
                                <img src={`http://localhost:8080/images/${currentMatch.imageUrl}`} className="w-11 h-11 rounded-full" />
                            )}
                            <div className={`max-w-xs px-4 py-2 rounded-2xl ${message.authorId === 'user' ? 'bg-blue-500 text-white ml-2' : 'bg-gray-200 text-gray-800 mr-2'}`}>
                                {message.messageText}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <div className="flex items-center">
                <input type="text" value={input} onChange={(e) => setInput(e.target.value)} onKeyPress={handleKeyPress} className="flex-1 border-2 border-gray-300 rounded-full py-2 px-4 mr-2 focus:outline-none focus:border-blue-500" placeholder="Type a message..." />
                <button className="bg-blue-500 text-white rounded-full p-2 hover:bg-blue-600 transition-colors duration-200" onClick={() => handleSend(conversation, input)}>
                    <Send size={24} />
                </button>
            </div>
        </div>
    ) : <div>Loading...</div>;
};

export default ChatScreen;
