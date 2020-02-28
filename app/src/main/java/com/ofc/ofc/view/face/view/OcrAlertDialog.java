package com.ofc.ofc.view.face.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.view.face.listener.OcrAlertDialogListener;

import androidx.appcompat.app.AppCompatDialog;

public class OcrAlertDialog extends AppCompatDialog implements View.OnClickListener{
    private Context context;
    private TextView tv_msg;
    private Button bt_close;
    private String msg;
    private OcrAlertDialogListener listener;

    public OcrAlertDialog(Context context, String msg, OcrAlertDialogListener listener) {
        super(context, R.style.BottomDialog);
        this.context = context;
        this.listener = listener;
        this.msg = msg;
        init(context);
    }

    @Override
    public void show() {
        super.show();
    }

    private void init(Context context) {
        setContentView(R.layout.cllc_aler_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        tv_msg = (TextView) findViewById(R.id.tv_msg);
        bt_close = (Button) findViewById(R.id.bt_close);
        bt_close.setOnClickListener(this);

        tv_msg.setText(msg);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_close:
                dismiss();
                listener.onClick(v);
                break;
        }
    }

    public void setMsg(String msg){
        tv_msg.setText(msg);
    }
}
