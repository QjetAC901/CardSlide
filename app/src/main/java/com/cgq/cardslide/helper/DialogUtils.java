package com.cgq.cardslide.helper;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

public class DialogUtils {

    /**
     * @param context
     * @param title     标题
     * @param message   内容
     * @param icon      图标
     * @param positive   左按钮文字
     * @param pListener 按钮单机监听器
     * @param negative  右按钮文字
     * @param nListener 按钮单机监听器
     * @return
     */
    public static android.app.AlertDialog createAlertDialog(Context context, String title,
                                                            String message, int icon, String positive,
                                                            DialogInterface.OnClickListener pListener, String negative,
                                                            DialogInterface.OnClickListener nListener, boolean isCancelable) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setCancelable(isCancelable);//弹出框不可以换返回键取消
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(icon);
        if (!TextUtils.isEmpty(positive)) {
            builder.setPositiveButton(positive, pListener);
        }
        if (!TextUtils.isEmpty(negative)) {
            builder.setNegativeButton(negative, nListener);
        }

        return builder.create();
    }

    /**
     * 创建V7包的弹出框
     *
     * @param context
     * @param title
     * @param message
     * @param icon
     * @param positive
     * @param pListener
     * @param negative
     * @param nListener
     * @param isCancelable
     * @return
     */
    public static AlertDialog createV7AlertDialog(Context context, String title,
                                                  String message, int icon, String positive,
                                                  DialogInterface.OnClickListener pListener, String negative,
                                                  DialogInterface.OnClickListener nListener, boolean isCancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(isCancelable);//弹出框不可以换返回键取消
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(icon);
        if (!TextUtils.isEmpty(positive)) {
            builder.setPositiveButton(positive, pListener);
        }
        if (!TextUtils.isEmpty(negative)) {
            builder.setNegativeButton(negative, nListener);
        }

        return builder.create();
    }


    /**
     * 进度弹出框
     *
     * @param context
     * @param text    文本
     * @return
     */
    public static ProgressDialog createProgressDialog(Context context, String text) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);
        pd.setIndeterminate(true);
        pd.setMessage(text);
        return pd;
    }
}
