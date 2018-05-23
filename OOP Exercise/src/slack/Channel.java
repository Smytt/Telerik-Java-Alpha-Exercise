package slack;

import java.util.HashMap;

class Channel {
    private String name;
    private HashMap<Integer, Message> messages;

    Channel(String name) {
        this.name = name;
        messages = new HashMap<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.put(message.id, message);
    }
}
