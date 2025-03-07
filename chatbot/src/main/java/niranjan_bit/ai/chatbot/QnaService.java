
package niranjan_bit.ai.chatbot;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class QnaService {

    // access to api key and url
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;


    private final WebClient webClient;

    public QnaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        // construct the request payload

        if (geminiApiUrl == null || geminiApiKey == null) {
            throw new IllegalStateException("API URL or API Key is not set");
        }
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", question)
                        })
                }
        );
        // make api call


        //  Corrected API key usage

        // above fn means we are making a post request, uri is going to be this, header is there, bodyValue is attaching the constructed payload as a requestBody
        // then retrieve is going to execute the request and expect a response, then we will convert the response which is requestBody to mono which is a reactive wrapper  containing a string.
        // then block will convert the reactive mono to a blocking call to get the actual string response


        // request response
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)  //  Corrected API key usage
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }
}
