package com.menuz.Utils;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.menuz.R;


public class NoConnectionDialog implements View.OnClickListener{

    private Dialog dialog;
    private Listner listner;

    public NoConnectionDialog(Context context, final Listner listner) {
        this.listner = listner;
        if (context!=null)
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_no_connection);
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        AppCompatButton btnRetry =  dialog.findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(this);

        dialog.setOnShowListener(dialogInterface -> {
            View view = dialog.getWindow().getDecorView();
            //for enter from left
            ObjectAnimator.ofFloat(view, "translationX", -view.getWidth(), 0.0f).start();
            //for enter from bottom
            ObjectAnimator.ofFloat(view, "translationY", view.getHeight(), 0.0f).start();
        });
    }


    public void show() {
        if (dialog != null)
            dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRetry) {
            listner.onNetworkChange(dialog, ConnectionDetector.isConnected());
        }
    }

    public interface Listner {
        void onNetworkChange(Dialog dialog, boolean isConnected);
    }
}
