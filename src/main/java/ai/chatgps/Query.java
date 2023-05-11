package ai.chatgps;

import lombok.Data;
import java.util.ArrayList;

class Choice{
    public Message message;
    public String finish_reason;
    public int index;
}

class Message{
    public String role;
    public String content;
}

public class Query {
    public String id;
    public String object;
    public int created;
    public String model;
    public Usage usage;
    public ArrayList<Choice> choices;
}

class Usage{
    public int prompt_tokens;
    public int completion_tokens;
    public int total_tokens;
}


