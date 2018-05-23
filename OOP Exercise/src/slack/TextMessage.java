package slack;

public class TextMessage extends Message implements Likable{
    final int id;
    private String text;
    TextMessage(String author, String text) {
        super(author);
        this.text = text;
        this.id = super.id;
    }

    @Override
    void show() {
        System.out.printf("%s: (%d) Text: %s\n", getAuthor(), id, text);
    }

    @Override
    public void like() {
        System.out.println("Text message liked!");
    }
}
