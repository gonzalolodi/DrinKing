package co.mobilemakers.drinking;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinalScoreFragment extends Fragment {

    public static final String WINNER = "winner";
    public static final String WINNER_TEAM = "winner team";
    public static final String WINNER_TEAM_BLUE = "Blue Team wins";
    public static final String WINNER_TEAM_RED = "Red Team wins";


    Bundle mBundle;
    String mGameMode;

    ImageView mImageViewWinner;
    TextView mTextViewTeamWinner;
    Button mButtonRestart;

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
        wiringUpWinnerView(rootView);
        prepareButtonRestart(rootView);
        displayingWinnerView();
        return rootView;
    }

    private void wiringUpWinnerView(View rootView) {
        mImageViewWinner = (ImageView) rootView.findViewById(R.id.image_view_winner);
        mTextViewTeamWinner = (TextView) rootView.findViewById(R.id.text_view_winner);
    }

    private void displayingWinnerView() {
        if (mGameMode.equals(SelectModeFragment.GAME_MODE_SOLO)) {
            Player winner = mBundle.getParcelable(WINNER);
            mImageViewWinner.setImageBitmap(getBitmap(winner));
            mTextViewTeamWinner.setText(String.format(getString(R.string.winner_solo_text), winner.getName()));
        } else {
            String teamWinner = mBundle.getString(FinalScoreFragment.WINNER_TEAM);
            mTextViewTeamWinner.setText(teamWinner);
            if (teamWinner.equals(WINNER_TEAM_BLUE)) {
                mImageViewWinner.setImageDrawable(getResources().getDrawable(R.drawable.keep_calm_blue));
                mTextViewTeamWinner.setTextColor(Color.BLUE);
            } else {
                mImageViewWinner.setImageDrawable(getResources().getDrawable(R.drawable.keep_calm_red));
                mTextViewTeamWinner.setTextColor(Color.RED);
            }
        }
    }

    private void prepareButtonRestart(View rootView) {
        mButtonRestart = (Button) rootView.findViewById(R.id.button_restart_game);
        mButtonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, new StartFragment()).commit();
            }
        });
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
