package application.slack;

public class Main {
    public static void main(String[] args) {
        Application slack = new Application();

        slack.createNewChannel(new Channel("spam"));
        slack.createNewChannel(new Channel("general"));
        slack.createNewChannel(new Channel("important"));
        slack.createNewChannel(new Channel("social"));

        slack.switchCurrentChannel("general");

        slack.postMessage(new TextMessage("Ivan", "Hello, I'm Ivan"));
        slack.postMessage(new ImageMessage("Ivan", Image.BMP));
        slack.postMessage(new IconMessage("Pesho", Icon.DOH));
        slack.postMessage(new FileMessage("Gosho", File.DOCX));

        slack.listAllMessages();

    }
}
