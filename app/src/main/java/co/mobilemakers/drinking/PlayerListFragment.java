package co.mobilemakers.drinking;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerListFragment extends ListFragment {

    PlayerAdapter mAdapter;
    Button mButtonAddPlayer;
    ArrayList<Player> mPlayers;
    Player mPlayer;


    final static Integer REQUEST_CODE = 1;

    public PlayerListFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_list, container, false);
        wireUpViews(rootView);
        List<Player> entries= new ArrayList<>();
        mAdapter = new PlayerAdapter(getActivity(), entries);
        setListAdapter(mAdapter);
        prepareButtonAddPlayerListener();
        return rootView;
    }

    private void prepareButtonAddPlayerListener() {
        mButtonAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddPlayerActivity.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    private void wireUpViews(View rootView) {
        mButtonAddPlayer=(Button)rootView.findViewById(R.id.button_add_player);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                mPlayers=this.getArguments().getParcelableArrayList("players");
                mPlayer = this.getArguments().getParcelable("player");
                mPlayers.add(mPlayer);
                Bundle extrasBundle = new Bundle();
                extrasBundle.putParcelableArrayList("players", mPlayers);
                mAdapter.add(mPlayer);





            }
        }
    }



}




