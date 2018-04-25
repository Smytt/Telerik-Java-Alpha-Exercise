package application.slack;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class Message {
    static int id = 0;
    private String author;
    private String date;

    Message(String author) {
        id++;
        this.author = author;
        this.date = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
    }

    public String getDate() {
        return date;
    }

    abstract void show();

    public String getAuthor() {
        return author;
    }

    public static int getId() {
        return id;
    }
}
