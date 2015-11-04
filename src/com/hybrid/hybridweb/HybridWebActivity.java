package com.hybrid.hybridweb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HybridWebActivity extends Activity {

	WebView myWeb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hybrid_web);

		myWeb = (WebView) findViewById(R.id.myWeb);

		WebSettings settings = myWeb.getSettings();
		settings.setJavaScriptEnabled(true);// java가 가능하게

		myWeb.addJavascriptInterface(new MyJavascriptInterface(), "android");

		myWeb.setWebViewClient(new MyWebViewClient());// a href()의 url를 위해
		myWeb.setWebChromeClient(new WebChromeClient()); // alert()가 가능하게 하기
		// 위해
		//myWeb.loadUrl("http://192.168.10.22:8080/web/index.jsp");
		myWeb.loadUrl("http://192.168.10.16:8080/web/index.jsp");
		//myWeb.loadUrl("http://www.soen.kr");

	}

	class MyJavascriptInterface {
		@JavascriptInterface
		public void showToast(String msg) {
			Log.i("###", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}

	}

	class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.i("###", "url = " + url);
			view.loadUrl(url);
			return true;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hybrid_web, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
