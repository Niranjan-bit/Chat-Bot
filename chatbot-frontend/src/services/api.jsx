import axios from "axios";

const API_URL = "https://thechatbot.onrender.com/api/qna/ask";

export const fetchChatResponse = async (question) => {
    try {
        const response = await axios.post(
            API_URL,
            { question }, 
            { headers: { "Content-Type": "application/json" } }
        );
        return response.data;
    } catch (error) {
        console.error("Error fetching response:", error);
        throw error;
    }
};
