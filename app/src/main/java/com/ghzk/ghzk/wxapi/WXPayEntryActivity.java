package com.ghzk.ghzk.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.utils.MyLogger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_permissions);
        
    	api = WXAPIFactory.createWXAPI(this, "wxe540385418282fe2");
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		MyLogger.i(TAG, "onPayFinish, errCode = " + resp.errCode);

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			/*AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.app_tip);
			builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
			builder.show();*/
			int errCord = resp.errCode;
			if (errCord == 0) {
				Toast.makeText(WXPayEntryActivity.this,getString(R.string.app_pay_true),Toast.LENGTH_LONG);
			} else {
				Toast.makeText(WXPayEntryActivity.this,getString(R.string.app_pay_false),Toast.LENGTH_LONG);
			}
			finish();
		}
	}
}