package co.mobilemakers.drinking;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinalScoreFragment extends Fragment {

    public static final String WINNER = "winner";
    public static final String WINNER_TEAM = "winner team";
    public static final String WINNER_TEAM_BLUE = "blue team wins";
    public static final String WINNER_TEAM_RED = "red team wins";


    Bundle mBundle;
    String mGameMode;

    ImageView mImageViewWinner;
    TextView mTextViewTeamWinner;

    public FinalScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_final_score, container, false);
        mBundle = this.getArguments();
        mGameMode = mBundle.getString(SelectModeFragment.GAME_MODE);
        mImageViewWinner = (ImageView) rootView.findViewById(R.id.image_view_winner);
        mTextViewTeamWinner = (TextView) rootView.findViewById(R.id.text_view_winner);
        if (mGameMode.equals(SelectModeFragment.GAME_MODE_SOLO)) {
            Player winner = mBundle.getParcelable(WINNER);
            mImageViewWinner.setImageBitmap(getBitmap(winner));
        } else {
            mTextViewTeamWinner.setText(mBundle.getString(FinalScoreFragment.WINNER_TEAM));
        }
        return rootView;
    }

    private Bitmap getBitmap(Player player) {
        Bitmap bmp;
        byte[] image;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        image = player.getImage();
        bmp = BitmapFactory.decodeByteArray(image, 0, image.length, options);
        return bmp;
    }


}
