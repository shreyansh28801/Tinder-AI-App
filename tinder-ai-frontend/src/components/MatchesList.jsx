import React from "react";

const MatchesList = ({ matches, onSelectMatch }) => {
    return (
        <div className="rounded-lg shadow-lg p-4">
            <h2 className="text-2xl font-bold mb-4">Matches</h2>
            <ul>
                {matches.map((match, index) => (
                    <li key={index} className="mb-2">
                        <button className="w-full hover:bg-gray-100 rounded flex item-center" onClick={() => onSelectMatch(match.profile, match.conversationId)}>
                            <img src={"http://127.0.0.1:8081/" + match.profile.imageUrl} className="w-16 h-16 rounded-full mr-3 object-cover" />
                            <span>
                <h3 className="font-bold">{match.profile.firstName} {match.profile.lastName}</h3>
              </span>
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default MatchesList;
