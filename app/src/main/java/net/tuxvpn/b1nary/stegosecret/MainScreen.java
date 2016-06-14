package net.tuxvpn.b1nary.stegosecret;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.sourceforge.openstego.*;


public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

    }

    //step 1
    public void selectImage(View view) {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 0);
    }

    //step 2
    //user inputs the string into the message box
    public void inputBox(View view) {

    }

    //step 3
    public void encodeMessage(View view) {

    }

    public void decodeMessage(View view) {

    }

    public void shareMessage(View view) {

    }

    //extra funcs
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    ((ImageView)findViewById(R.id.stegoImage)).setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    ((ImageView)findViewById(R.id.stegoImage)).setImageURI(selectedImage);
                }
                break;
        }
    }
}
