package com.jaykallen.samples.deeplinking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jaykallen.samples.R;


// 6-5-17:  Most of the configuration for deep links is within the manifest.
// You have to setup Run - Edit Configuration, Launch URl, then enter jaykallen://deeplinks
// to test out the Deep links usage.
//         <!-- <activity android:name=".DeepLinkActivity" android:label="deeplink">
//             <intent-filter>
//                 <action android:name="android.intent.action.VIEW" />
//                 <category android:name="android.intent.category.BROWSABLE" />
//                 <category android:name="android.intent.category.DEFAULT" />
//                 <data android:host="deeplink" android:scheme="jaykallen" />
//                 <data android:host="www.jaykallen.com" android:scheme="http" />
//             </intent-filter>
//         </activity> -->

public class DeepLinkActivity extends AppCompatActivity {
    TextView mDescTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
        mDescTV = (TextView) findViewById(R.id.desc_textview);

        Intent intent = getIntent();
        Uri data = intent.getData();
        mDescTV.setText("You've been deep linked!!");
    }


}
