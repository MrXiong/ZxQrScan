package com.zbar.lib;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zbar.lib.CaptureBaseActivity;
import com.zbar.lib.camera.CameraManager;
import com.zx.qrscan.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CaptureActivity extends CaptureBaseActivity {

	public static final String SCANTYPE = "scan_type";
	@Bind(R.id.sv_background)
	SurfaceView mSvBackground;
	@Bind(R.id.iv_scan_line)
	ImageView mIvScanLine;
	@Bind(R.id.rv_crop)
	RelativeLayout mRvCrop;
	@Bind(R.id.rv_containter)
	RelativeLayout mRvContainter;


	private String mScanType;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr_scan);
		ButterKnife.bind(this);
		initData();
		initView();
	}

	private void initData() {
		mScanType = getIntent().getStringExtra(SCANTYPE);

	}

	private void initView() {
		setRvCrop(mRvCrop);
		setRvContainter(mRvContainter);
		setSvBackground(mSvBackground);
		setCaptureActivity(this);
		mIvScanLine.setAnimation(mAnimation);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void handleDecode(String result) {
		super.handleDecode();
		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
	}

	protected void initCamera(SurfaceHolder surfaceHolder) {
		super.initCamera(surfaceHolder);
	}





	//甲骨文拓展
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem onLight = menu.add(0, 1, 1, OPEN);
		onLight.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return super.onCreateOptionsMenu(menu);
	}

	private static final String OPEN = "开灯";
	private static final String OFF = "关灯";
	boolean isOpened = true;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			if (isOpened) {
				isOpened = false;
				item.setTitle(OFF);
				CameraManager.get().openLight();
			} else {
				isOpened = true;
				item.setTitle(OPEN);
				CameraManager.get().offLight();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}