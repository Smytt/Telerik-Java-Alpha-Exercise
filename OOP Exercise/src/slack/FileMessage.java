package slack;

public class FileMessage extends Message implements Downloadable {
    final int id;
    private File file;

    FileMessage(String author, File file) {
        super(author);
        this.file = file;
        this.id = super.id;
    }

    @Override
    void show() {
        System.out.printf("%s: (%d) File: %s\n", getAuthor(), id, file);
    }

    @Override
    public void download() {
        System.out.println(file + " downloaded!");
    }
}
