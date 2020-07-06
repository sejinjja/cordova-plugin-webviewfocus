package kr.sejiwork.cordova.webviewfocus;

import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.util.Log;

public class Webviewfocus extends CordovaPlugin {
  private String applicationId;

  @Override
  public void initialize (CordovaInterface cordova, CordovaWebView webView) {
    this.webView = webView;
  }

	@Override
	public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        this.applicationId = preferences.getString("applicationId", this.applicationId);
		if ("requestFocus".equals(action)) {
		  this.requestFocus(callbackContext);
			return true;
		}
		return false;
	}

	private void requestFocus(CallbackContext callbackContext) {
		try {
      if (! cordova.getActivity().getWindow().getDecorView().hasFocus()) {
        cordova.getActivity().getWindow().getDecorView().requestFocus();
      }
			callbackContext.success();
		} catch (Exception e) {
			callbackContext.error(getPrintStackTrace(e));
		}
	}

	private static String getPrintStackTrace(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		Log.e("cordova-plugin-webviewfocus", errors.toString());

		return errors.toString();
	}

}
