package co.mobilemakers.drinking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    Button mButtonStartGame;
    Button mButtonAddChallenge;
    Button mButtonRules;


    public StartFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        wireUpButtons(rootView);
        mButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

    private void wireUpButtons(View rootView) {
        mButtonStartGame=(Button)rootView.findViewById(R.id.button_start_game);
        mButtonAddChallenge=(Button)rootView.findViewById(R.id.button_add_challenge);
        mButtonRules=(Button)rootView.findViewById(R.id.button_rules);
    }
}