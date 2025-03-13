import axios from "axios";

// Use the deployed backend URL instead of localhost
const API_URL = "https://thechatbot.onrender.com/api/qna/ask";

export const fetchChatResponse = async (question) => {
    try {
        const response = await axios.post(API_URL, { question });
        return response.data;
    } catch (error) {
        console.error(error);
        throw error;
    }
};
