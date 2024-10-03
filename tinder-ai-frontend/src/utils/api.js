export const fetchRandomProfile = async () => {
    const response = await fetch('http://localhost:8080/profiles/random');
    if (!response.ok) {
        throw new Error('Failed to fetch profile');
    }
    return response.json();
}

export const saveSwipe = async (profileId) => {
    const response = await fetch('http://localhost:8080/matches', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ profileId })
    });
    if (!response.ok) {
        throw new Error('Failed to save swipe');
    }
}

export const fetchMatches = async () => {
    const response = await fetch('http://localhost:8080/matches');
    if (!response.ok) {
        throw new Error('Failed to fetch matches');
    }
    return response.json();
}

export const fetchConversation = async (conversationId) => {
    const response = await fetch(`http://localhost:8080/conversation/${conversationId}`);
    if (!response.ok) {
        throw new Error('Failed to fetch conversation');
    }
    return response.json();
}

export const sendMessage = async (conversationId, message) => {
    const response = await fetch(`http://localhost:8080/addMessageToconversation/${conversationId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ messageText: message, authorId: "user" })
    });
    if (!response.ok) {
        throw new Error('Failed to submit message');
    }
    return response.json();
}
