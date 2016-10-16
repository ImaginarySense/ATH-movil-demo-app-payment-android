package athqrpay.ddg.com.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import athqrpay.ddg.com.athmovilpaysdkandroid.ATHMovilPaySDK;

public class AMDRX480 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatCallback callback = new AppCompatCallback() {
            @Override
            public void onSupportActionModeStarted(ActionMode actionMode) {
            }

            @Override
            public void onSupportActionModeFinished(ActionMode actionMode) {
            }

            @Nullable
            @Override
            public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
                return null;
            }
        };

        AppCompatDelegate delegate = AppCompatDelegate.create(this, callback);

        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.amd_rx_480);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_other_awesome_toolbar_2);
        delegate.setSupportActionBar(toolbar);

        ImageView pay_button = (ImageView) findViewById(R.id.pay_button);
        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ATHMovilPaySDK sdk = new ATHMovilPaySDK();
                sdk.requestPayment(280, "787-321-4321", "id12345678", "athmovildemoapp://amd", AMDRX480.this);
            }
        });

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        Log.v("INTENT REACHED", "TEST!!!");

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }


        }
    }

    private void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        ATHMovilPaySDK sdk = new ATHMovilPaySDK();
        String result = sdk.verifyPayment(sharedText);
        if (result.contains("SUCCESS")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Transaction completed!", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}