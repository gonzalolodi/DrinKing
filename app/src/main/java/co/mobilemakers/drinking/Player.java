package co.mobilemakers.drinking;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Agustin on 16/02/2015.
 */
public class Player implements Parcelable {


    private String name;
    private String team;
    private byte[] image;
    private int score;


    public Player(String name, String team, byte[] image) {
        this.name = name;
        this.team = team;
        this.image = image;
        this.score = 0;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }



    private Player(Parcel source) {

        name =source.readString();
        team =source.readString();
        image = new byte[source.readInt()];
        source.readByteArray(image);
        score= source.readInt();

    }



    @Override
    public int describeContents() {
        return 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(team);
        dest.writeInt(image.length);
        dest.writeByteArray(image);
        dest.writeInt(score);
    }
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {

        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };


}
