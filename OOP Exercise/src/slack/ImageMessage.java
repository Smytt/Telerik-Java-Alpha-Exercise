package slack;

public class ImageMessage extends Message implements Likable, Downloadable{
    final int id;

    private Image image;
    ImageMessage(String author, Image image) {
        super(author);
        this.image = image;
        this.id = super.id;
    }

    @Override
    void show() {
        System.out.printf("%s: (%d) Image: %s\n", getAuthor(), id, image);
    }

    @Override
    public void like() {
        System.out.println(image + " liked!");
    }

    @Override
    public void download() {
        System.out.println(image + " downloaded!");
    }
}
