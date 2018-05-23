package slack;

public class IconMessage extends Message {
    final int id;
    private Icon icon;

    IconMessage(String author, Icon icon) {
        super(author);
        this.icon = icon;
        this.id = super.id;
    }

    @Override
    void show() {
        System.out.printf("%s: (%d) Icon: %s\n", getAuthor(), id, icon);
    }
}
