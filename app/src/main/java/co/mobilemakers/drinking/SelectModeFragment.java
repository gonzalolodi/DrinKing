package co.mobilemakers.drinking;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectModeFragment extends Fragment {

    Switch mSwitchGameMode;
    Button mButtonAddPlayers;

    public static final String GAME_MODE = "game mode";


    public SelectModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_select_mode, container, false);
        wireUpSwitch(rootView);
        mButtonAddPlayers = (Button) rootView.findViewById(R.id.button_add_players);
        mButtonAddPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayerListActivity.class);
                String gameMode = getGameMode();
                intent.putExtra(GAME_MODE, gameMode);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private String getGameMode() {
        String gameMode;
        if (mSwitchGameMode.isChecked()){
            gameMode="Team";
        }
        else{
            gameMode="Solo";
        }
        return gameMode;
    }

    private void wireUpSwitch(View rootView) {
        mSwitchGameMode = (Switch) rootView.findViewById(R.id.switch_game_mode);
    }



}
