package net.tuxvpn.b1nary.stegosecret;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.sourceforge.openstego.OpenStegoCmd;
import net.sourceforge.openstego.util.PluginManager;

import java.io.InputStream;


public class MainScreen extends AppCompatActivity {
    private static final String TAG = "StegoSecret";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


    }

    //step 1
    public void selectImage(View view) {

        /*
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        */

        Intent pickPhoto = new Intent();
        pickPhoto.setType("image/*");
        pickPhoto.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pickPhoto, PICK_IMAGE);

    }

    //step 2
    //user inputs the string into the message box
    public void inputBox(View view) {

    }

    //step 3
    public void encodeMessage(View view) {
        try {
            PluginManager.loadPlugins();
            String[] args = {
                    "embed",
                    "-a",
                    "RandomLSB",
                    "-mf",
                    "/sdcard/secret.txt",
                    "-cf",
                    "/sdcard/testimage.png",
                    "-sf",
                    "/sdcard/testoutput.png"};
            OpenStegoCmd.execute(args);
        } catch (Exception e) {
            Log.e(TAG, "OpenStego Failed with message:");
            Log.e(TAG, e.getMessage());
        }
    }

    public void decodeMessage(View view) {

    }

    public void shareMessage(View view) {

    }

    //extra funcs

    static final int PICK_IMAGE = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(imageReturnedIntent.getData());

                        Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);

                        ImageView stegoImage = (ImageView) findViewById(R.id.stegoImage);
                        TextView textView = (TextView) findViewById(R.id.step1);

                        if (stegoImage != null) {
                            stegoImage.setImageDrawable(null);
                            stegoImage.setImageBitmap(imageBitmap);
                        } else {
                            throw new NullPointerException("Null imageView pointer.");
                        }

                        if (textView != null) {
                            textView.setText("");
                        } else {
                            throw new NullPointerException("Null textView pointer.");
                        }

                        if (imageStream != null) {
                            imageStream.close();
                        }

                    } catch (Exception e) {
                        Log.e(TAG, "Error! ");
                        Log.e(TAG, e.getMessage());
                    }
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        ImageView stegoImage = (ImageView) findViewById(R.id.stegoImage);
                        TextView textView = (TextView) findViewById(R.id.step1);
                        if (stegoImage != null) {
                            stegoImage.setImageDrawable(null);
                            stegoImage.setImageURI(selectedImage);
                        } else {
                            throw new NullPointerException("Null imageView pointer.");
                        }

                        if (textView != null) {
                            textView.setEnabled(false);
                        } else {
                            throw new NullPointerException("Null textView pointer.");
                        }

                    } catch (Exception e) {
                        Log.e(TAG, "Error! ");
                        Log.e(TAG, e.getMessage());
                    }
                }
                break;
        }
    }
}
