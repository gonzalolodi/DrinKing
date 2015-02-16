package co.mobilemakers.drinking;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddChallengeFragment extends Fragment {

    private static final String LOG_TAG = AddChallengeFragment.class.getSimpleName();
    DatabaseHelper mDBHelper;

    EditText mEditTextNewChallengeName;
    EditText mEditTextNewChallengeContent;
    Button mButtonAddChallenge;

    public AddChallengeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_challenge, container, false);
        wireUpEditTexts(rootView);
        mButtonAddChallenge = (Button) rootView.findViewById(R.id.button_add_new_challenge_done);
        mButtonAddChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Challenge challenge = new Challenge(mEditTextNewChallengeName.getText().toString(),mEditTextNewChallengeContent.getText().toString());
                try {
                    Dao<Challenge,Integer> dao = getDBHelper().getContactDao();
                    dao.create(challenge);
                } catch (SQLException e) {
                    Log.e(LOG_TAG, "Failed to create DAO.", e);
                }
            }
        });
        return rootView;
    }

    private void wireUpEditTexts(View rootView) {
        mEditTextNewChallengeName = (EditText)rootView.findViewById(R.id.edit_text_new_challenge_name);
        mEditTextNewChallengeContent = (EditText) rootView.findViewById(R.id.edit_text_new_challenge_content);
    }

    public DatabaseHelper getDBHelper() {
        if (mDBHelper == null){
            mDBHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return mDBHelper;
    }


}
