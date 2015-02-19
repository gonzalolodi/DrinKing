package co.mobilemakers.drinking;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Gonzalo on 16/02/2015.
 */
public class Challenge {

    final static String PING_PONG_BALL = "ping_pong_balls_preference";
    final static String CARDS = "cards_preference";
    final static String DICE = "dice_preference";
    final static String PLASTIC_CUPS = "plastic_cups_preference";
    public static final String NO_TOOLS = "no tools";

    public static final String NAME = "name";
    public static final String CONTENT = "content";
    public static final String ID = "_id";
    public static final String TOOL = "tool";

    @DatabaseField(generatedId = true, columnName = ID) private int id;
    @DatabaseField(columnName = NAME) private String name;
    @DatabaseField(columnName = CONTENT) private String content;
    @DatabaseField(columnName = TOOL) private String tool;

    public Challenge() {
    }

    public Challenge(String name, String content, String tool) {
        this.content = content;
        this.name = name;
        this.tool = tool;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
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
