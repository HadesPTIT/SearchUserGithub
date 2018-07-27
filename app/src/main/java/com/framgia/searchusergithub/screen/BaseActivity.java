package com.framgia.searchusergithub.screen;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.framgia.searchusergithub.R;
import com.framgia.searchusergithub.utils.NetworkReceiver;

public class BaseActivity extends AppCompatActivity {

    protected NetworkReceiver mNetworkReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        if (mNetworkReceiver == null) {
            return;
        }
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mNetworkReceiver == null) {
            return;
        }
        unregisterReceiver(mNetworkReceiver);
    }

    public void setupNetworkBroadcastReceiver(NetworkReceiver.NetworkStateListener listener) {
        mNetworkReceiver = new NetworkReceiver(listener);
    }

    protected void showDialogNoInternet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.msg_no_network_available));
        AlertDialog dialog = builder.create();
        if (dialog.isShowing()) {
            return;
        }
        dialog.show();
    }
}
