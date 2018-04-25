package application.slack;

import java.util.ArrayList;
import java.util.HashMap;

public class Application {
    private HashMap<String, Channel> channels;
    private Channel currentChannel;

    Application() {
        channels = new HashMap<>();
    }

    void createNewChannel(Channel channel) {
        channels.put(channel.getName(), channel);
        switchCurrentChannel(channel.getName());
    }

    public Channel getCurrentChannel() {
        return currentChannel;
    }

    public void switchCurrentChannel(String name) {
        currentChannel = channels.get(name);
    }

    public void postMessage(Message message) {
        currentChannel.addMessage(message);
        message.show();
    }

    public void listAllMessages() {
        System.out.printf("All messages in %s:\n", currentChannel.getName());
        System.out.println("===================");
        currentChannel.getMessages().forEach((date, message) -> message.show());
    }

    public void clearHistory() {
        currentChannel.getMessages().clear();
    }
}
