package com.pt.movieticket.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.pt.movieticket.R;

/**
 * Created by Trang on 6/29/2016.
 */
public class DialogUtil {

    public interface IDialogConfirm {
        void onClickOk();
    }

    public static void showAlertDialog(Context context, int message , final IDialogConfirm iDialogConfirm){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                iDialogConfirm.onClickOk();
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel),new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

}
