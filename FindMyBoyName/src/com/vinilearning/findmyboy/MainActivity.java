package com.vinilearning.findmyboy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {
	private EditText etName;
	private EditText etAllName;
	private Button btnFind;
	private TextView tvResult;
	private LoveCalculator loveCalculator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loveCalculator = new LoveCalculator();
		initView();

	}

	private void initView() {
		etName = (EditText) findViewById(R.id.etGirl);
		etAllName = (EditText) findViewById(R.id.etAllName);
		btnFind = (Button) findViewById(R.id.btnFind);
		tvResult = (TextView) findViewById(R.id.tvResult);
		btnFind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String girl = etName.getText().toString().trim();
				String boys = etAllName.getText().toString().trim();
				String[] allName = null;
				if (TextUtils.isEmpty(boys)) {
					Toast.makeText(MainActivity.this,
							getString(R.string.not_enter_name_of_boys),
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (TextUtils.isEmpty(girl)) {
					Toast.makeText(MainActivity.this,
							getString(R.string.not_enter_your_name),
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (boys.contains(",")) {
					allName = boys.split(",");
				} else {
					allName = new String[] { boys };
				}

				if (allName != null && allName.length > 0) {
					tvResult.setText(loveCalculator.findBoy(girl, allName));
				}

			}
		});

		(new Handler()).postDelayed(new Runnable() {

			@Override
			public void run() {
				loadAdsView();

			}
		}, 1000);
	}

	/**
	 * Method used to load ads view.
	 * 
	 * @param rootView
	 */
	private void loadAdsView() {
		AdView adView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}
}
