import React from "react";
import { X, Heart } from "lucide-react";

const ProfileSelector = ({ profile, onSwipe }) => (
    profile ? (
        <div>
            <div className="rounded-lg overflow-hidden bg-white shadow-lg">
                <div className="relative">
                    <img src={"http://127.0.0.1:8081/" + profile.imageUrl} />
                    <div className="absolute bottom-0 left-0 right-0 text-white p-4 bg-gradient-to-t from-black">
                        <h2 className="text-3xl font-bold">{profile.firstName} {profile.lastName}, {profile.age}</h2>
                    </div>
                </div>
                <div className="p-4">
                    <p className="text-gray-600 mb-4">{profile.bio}</p>
                </div>
                <div className="p-4 flex justify-center space-x-4">
                    <button className="bg-red-500 rounded-full p-4 text-white hover:bg-red-700" onClick={() => onSwipe(profile.id, "left")}>
                        <X size={24} />
                    </button>
                    <button className="bg-green-500 rounded-full p-4 text-white hover:bg-green-700" onClick={() => onSwipe(profile.id, "right")}>
                        <Heart size={24} />
                    </button>
                </div>
            </div>
        </div>
    ) : <div>Loading...</div>
);

export default ProfileSelector;
