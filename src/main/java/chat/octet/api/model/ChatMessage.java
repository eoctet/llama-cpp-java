package chat.octet.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMessage {
    private ChatRole role;
    private String content;

    public ChatMessage() {
    }

    public ChatMessage(ChatRole role, String content) {
        this.role = role;
        this.content = content;
    }

    public enum ChatRole {
        SYSTEM, USER, ASSISTANT
    }
}