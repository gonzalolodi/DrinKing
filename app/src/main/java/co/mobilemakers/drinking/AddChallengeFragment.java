package co.mobilemakers.drinking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

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
    RadioGroup mRadioGroupTools;
    String mTool;

    public AddChallengeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_challenge, container, false);
        wireUpEditTexts(rootView);
        prepareRadioGroupAndGetPickedButton(rootView);
        prepareButtonAndSetOnClickListener(rootView);

        return rootView;
    }

    private void prepareButtonAndSetOnClickListener(View rootView) {
        mButtonAddChallenge = (Button) rootView.findViewById(R.id.button_add_new_challenge_done);
        mButtonAddChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Challenge challenge = new Challenge(mEditTextNewChallengeName.getText().toString(),mEditTextNewChallengeContent.getText().toString(), mTool);
                try {
                    Dao<Challenge,Integer> dao = getDBHelper().getContactDao();
                    dao.create(challenge);
                } catch (SQLException e) {
                    Log.e(LOG_TAG, "Failed to create DAO.", e);
                }
            }
        });
    }

    private void prepareRadioGroupAndGetPickedButton(View rootView) {
        mRadioGroupTools = (RadioGroup) rootView.findViewById(R.id.radio_group_tool);
        switch (mRadioGroupTools.getCheckedRadioButtonId()){
            case R.id.radio_button_cards:
                mTool = Challenge.CARDS;
                break;
            case R.id.radio_button_dice:
                mTool = Challenge.DICE;
                break;
            case R.id.radio_button_ping_pong_ball:
                mTool = Challenge.PING_PONG_BALL;
                break;
            case R.id.radio_button_plastic_cups:
                mTool = Challenge.PLASTIC_CUPS;
                break;
            case R.id.radio_button_no_tools:
                mTool = Challenge.NO_TOOLS;
                break;
        }
        ;
    }

    private void wireUpEditTexts(View rootView) {
        mEditTextNewChallengeName = (EditText)rootView.findViewById(R.id.edit_text_new_challenge_name);
        mEditTextNewChallengeContent = (EditText) rootView.findViewById(R.id.edit_text_new_challenge_content);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mButtonAddChallenge.setEnabled(!TextUtils.isEmpty(mEditTextNewChallengeContent.getText())
                        && !TextUtils.isEmpty(mEditTextNewChallengeName.getText()));
            }
        };
        mEditTextNewChallengeName.addTextChangedListener(textWatcher);
        mEditTextNewChallengeContent.addTextChangedListener(textWatcher);
    }

    public DatabaseHelper getDBHelper() {
        if (mDBHelper == null){
            mDBHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return mDBHelper;
    }


}
