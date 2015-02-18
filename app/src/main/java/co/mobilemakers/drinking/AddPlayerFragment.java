package co.mobilemakers.drinking;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlayerFragment extends Fragment {


    Button mButtonConfirmPlayer;
    ImageButton mPhotoPlayer;
    EditText mEditTextPlayername;
    Switch mSwitchTeam;
    Player mPlayer;
    String mName, mTeam;
    final static Integer REQUEST_CODE = 0;
    Bitmap mPhoto;
    byte[] mImage;

    public AddPlayerFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_player, container, false);
        wireUpViews(rootView);
        prepareImageButton();
        prepareConfirmButtonListener();
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddPlayerFragment.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            mPhoto = (Bitmap) data.getExtras().get("data");
            mPhotoPlayer.setImageBitmap(mPhoto);
        }
    }


    private void prepareConfirmButtonListener() {
        mButtonConfirmPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preparePlayer();
                mPlayer = new Player(mName, mTeam, mImage);
                Bundle extrasBundle = new Bundle();
                extrasBundle.putParcelable("player",mPlayer);
                Activity activity = getActivity();
                Intent intentResult = new Intent();
                intentResult.putExtras(extrasBundle);
                activity.setResult(Activity.RESULT_OK, intentResult);
                activity.finish();
            }
        });
    }

    private void preparePlayer() {
        mName=mEditTextPlayername.getText().toString();
        if (mSwitchTeam.isChecked()){
            mTeam="Red";
        }
        else{
            mTeam="Blue";
        }
        convertBitmapImageToByteArray();
    }


    private void wireUpViews(View rootView) {
        mButtonConfirmPlayer=(Button) rootView.findViewById(R.id.button_confirm_player);
        mPhotoPlayer=(ImageButton)rootView.findViewById(R.id.image_button_player_photo);
        mEditTextPlayername=(EditText)rootView.findViewById(R.id.edit_text_player_name);
        mSwitchTeam=(Switch)rootView.findViewById(R.id.switch_player_team);
    }

    private void convertBitmapImageToByteArray() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mPhoto.compress(Bitmap.CompressFormat.PNG, 100, stream);
        mImage = stream.toByteArray();
    }

    private void prepareImageButton() {

        mPhotoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, AddPlayerFragment.REQUEST_CODE);
            }
        });
    }
}


