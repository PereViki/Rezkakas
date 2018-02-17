package com.example.android.rezkakas;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make the status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.activity_main);
    }

    /**
     * Intents for the contact's of the company
     * with a SWITCH statement based on the view
     * the user clicked on
     *
     * @param view The view the user clicked on
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.map_icon:
            case R.id.company_address:
                // Open map at the company's geolocation
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(Uri.parse("geo:0,0?q=" + getString(R.string.company_lat) + "," + getString(R.string.company_long) + "(" + getString(R.string.company_name) + ")"));
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;
            case R.id.phone_icon:
            case R.id.company_phone:
                // Open dialer with the company's phone no
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getString(R.string.company_telephone)));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.web_icon:
            case R.id.company_web:
                // Open the company's website in the browser
                Uri webpage = Uri.parse("http://" + getString(R.string.company_web));
                Intent intentW = new Intent(Intent.ACTION_VIEW, webpage);
                if (intentW.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentW);
                }
                break;
            case R.id.mail_icon:
            case R.id.company_email:
                // Open the mail app with the company's e-mail address as a recipient
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto:" + getString(R.string.company_email))); // only email apps should handle this
                if (mailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mailIntent);
                }
                break;
        }
    }
}
