package co.mobilemakers.drinking;

/**
 * Created by Gonzalo on 16/02/2015.
 */
public class Challenge {

    public static final String NAME = "name";
    public static final String CONTENT = "content";
    public final static String ID = "_id";

    private int id;
    private String name;
    private String content;

    public Challenge() {
    }

    public Challenge(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
