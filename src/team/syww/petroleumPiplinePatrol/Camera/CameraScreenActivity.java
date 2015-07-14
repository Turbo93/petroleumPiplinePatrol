package team.syww.petroleumPiplinePatrol.Camera;

import java.util.Timer;
import java.util.TimerTask;

import team.syww.petroleumPiplinePatrol.MainActivity.MainActivity;

import com.etc.syww.petroleumPiplinePatrol.R;

import dji.sdk.api.DJIDrone;
import dji.sdk.api.DJIError;
import dji.sdk.api.Camera.DJICameraFileNamePushInfo;
import dji.sdk.api.Camera.DJICameraPlaybackState;
import dji.sdk.api.Camera.DJICameraSettingsTypeDef.CameraMode;
import dji.sdk.api.DJIDroneTypeDef.DJIDroneType;
import dji.sdk.interfaces.DJICameraFileNameInfoCallBack;
import dji.sdk.interfaces.DJICameraPlayBackStateCallBack;
import dji.sdk.interfaces.DJIExecuteResultCallback;
import dji.sdk.interfaces.DJIGerneralListener;
import dji.sdk.interfaces.DJIReceivedVideoDataCallBack;
import dji.sdk.widget.DjiGLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CameraScreenActivity extends Activity implements OnClickListener {
	private static final String TAG = "CameraScreenActivity";
	private Context m_context;
	private DJIReceivedVideoDataCallBack mReceivedVideoDataCallBack = null;
	private DJICameraFileNameInfoCallBack mCameraFileNameInfoCallBack = null;
	private DJICameraPlayBackStateCallBack mCameraPlayBackStateCallBack = null;
	private String mPlayBackStateString = "";
	private Timer mTimer;
	private int setValue = 0;
	private TextView mCameraPlaybackStateTV;
	private int mPlayBackThumbnailNum = -1;
	private int mPlayBackMediaFileNum = -1;
	private int mPlayBackCurrentSelectIndex = -1;
	private final int SHOWTOAST = 1;
	private final int SHOW_DOWNLOAD_PROGRESS_DIALOG = 2;
	private final int HIDE_DOWNLOAD_PROGRESS_DIALOG = 3;

	private ImageButton m_ibt_return;
	private DjiGLSurfaceView mDjiGLSurfaceView;
	private ImageButton m_ibt_battery;
	private ImageButton m_ibt_wifi;
	private ImageButton m_ibt_satellite;
	private ImageButton m_ibt_autocamera;
	private ImageButton m_ibt_shoot;
	private ImageButton m_ibt_onekeytakeoff;
	private ImageButton m_ibt_onekeytoreturn;
	private ImageButton m_ibt_location;
	private ImageButton m_ibt_mapping;
	private TextView m_txt_height;
	private TextView m_txt_showheight;
	private TextView m_txt_speed;
	private TextView m_txt_showspeed;
	private TextView m_txt_showwifi;
	private TextView m_txt_showbattery;
	private TextView m_txt_showsatellite;

	/**
	 * 定时器事件，用于检测飞行器连接状态
	 */
	class Task extends TimerTask {
		public void run() {
			checkConnectState();
		}
	};

	/**
	 * 消息监听器，用于子线程向主线程传输消息
	 */
	private Handler handler = new Handler(new Handler.Callback() {
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case SHOWTOAST:
				Toast.makeText(CameraScreenActivity.this, (String) msg.obj,
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
			return false;
		}
	});

	private void initPhantom3Pro() {
		// 初始化飞行器型号为Phantom3Pro，DJIDrone_Inspire1表示型号为Phantom3Pro
		DJIDrone.initWithType(this.getApplicationContext(),
				DJIDroneType.DJIDrone_Inspire1);

		// 连接飞行器
		DJIDrone.connectToDrone();

		// 进行权限检查
		new Thread() {
			public void run() {
				try {
					DJIDrone.checkPermission(getApplicationContext(),
							new DJIGerneralListener() {
								public void onGetPermissionResult(int result) {
									Log.e(TAG, "onGetPermissionResult = "
											+ result);
									Log.e(TAG,
											"onGetPermissionResultDescription = "
													+ DJIError
															.getCheckPermissionErrorDescription(result));
								}
							});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_screen);

		// 找到相关视图的id
		findViewId();

		// 初始化Phantom3Pro
		initPhantom3Pro();

		// 初始化飞行器相机
		initCamera();

		this.m_ibt_autocamera.setOnClickListener(this);
		this.m_ibt_return.setOnClickListener(this);
		this.m_ibt_location.setOnClickListener(this);
		this.m_ibt_shoot.setOnClickListener(this);
		this.m_ibt_mapping.setOnClickListener(this);
		this.m_ibt_onekeytakeoff.setOnClickListener(this);
		this.m_ibt_onekeytoreturn.setOnClickListener(this);
	}

	/**
	 * 按钮监听事件
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibt_autocamera:
			DJIDrone.getDjiCamera().startTakePhoto(
					new DJIExecuteResultCallback() {
						public void onResult(DJIError mErr) {
							Log.d(TAG, "Start Takephoto errorCode = "
									+ mErr.errorCode);
							Log.d(TAG, "Start Takephoto errorDescription = "
									+ mErr.errorDescription);
							String result = "errorCode ="
									+ mErr.errorCode
									+ "\n"
									+ "errorDescription ="
									+ DJIError
											.getErrorDescriptionByErrcode(mErr.errorCode);

							handler.sendMessage(handler.obtainMessage(
									SHOWTOAST, result));
						}
					});
			break;
		case R.id.ibt_return:
			Intent intent = new Intent();
			intent.setClass(CameraScreenActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.ibt_mapping:
			break;
		case R.id.ibt_location:
			break;
		case R.id.ibt_onekeytakeoff:
			break;
		case R.id.ibt_onekeytoreturn:
			break;
		case R.id.ibt_shoot:
			break;
		default:
			break;
		}
	}

	private void checkConnectState() {
		CameraScreenActivity.this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				boolean bConnectState = DJIDrone.getDjiCamera()
						.getCameraConnectIsOk();
				if (bConnectState) {

				} else {
					m_ibt_autocamera.setEnabled(false);
					m_ibt_shoot.setEnabled(false);
					m_ibt_onekeytakeoff.setEnabled(false);
					m_ibt_onekeytoreturn.setEnabled(false);
					m_ibt_battery.setEnabled(false);
					m_ibt_wifi.setEnabled(false);
					m_ibt_satellite.setEnabled(false);
				}
			}
		});
	}

	private void findViewId() {
		m_ibt_return = (ImageButton) findViewById(R.id.ibt_return);
		mDjiGLSurfaceView = (DjiGLSurfaceView) findViewById(R.id.DjiSurfaceView_02);
		m_ibt_battery = (ImageButton) findViewById(R.id.ibt_battery);
		m_ibt_wifi = (ImageButton) findViewById(R.id.ibt_wifi);
		m_ibt_satellite = (ImageButton) findViewById(R.id.ibt_satellite);
		m_ibt_autocamera = (ImageButton) findViewById(R.id.ibt_autocamera);
		m_ibt_shoot = (ImageButton) findViewById(R.id.ibt_shoot);
		m_ibt_onekeytakeoff = (ImageButton) findViewById(R.id.ibt_onekeytakeoff);
		m_ibt_onekeytoreturn = (ImageButton) findViewById(R.id.ibt_onekeytoreturn);
		m_ibt_location = (ImageButton) findViewById(R.id.ibt_location);
		m_ibt_mapping = (ImageButton) findViewById(R.id.ibt_mapping);
		m_txt_height = (TextView) findViewById(R.id.txt_height);
		m_txt_showheight = (TextView) findViewById(R.id.txt_showheight);
		m_txt_speed = (TextView) findViewById(R.id.txt_speed);
		m_txt_showspeed = (TextView) findViewById(R.id.txt_showspeed);
		m_txt_showwifi = (TextView) findViewById(R.id.txt_showwifi);
		m_txt_showbattery = (TextView) findViewById(R.id.txt_showbattery);
		m_txt_showsatellite = (TextView) findViewById(R.id.txt_showsatellite);
	}

	private void initCamera() {
		// 开启SurfaceView，准备接受相机图像
		mDjiGLSurfaceView.start();

		// 设置相机图像数据缓存
		mReceivedVideoDataCallBack = new DJIReceivedVideoDataCallBack() {
			public void onResult(byte[] videoBuffer, int size) {
				mDjiGLSurfaceView.setDataToDecoder(videoBuffer, size);
			}
		};
		DJIDrone.getDjiCamera().setReceivedVideoDataCallBack(
				mReceivedVideoDataCallBack);

		// 设置相机模式，开始传输相机图像
		DJIDrone.getDjiCamera().setCameraMode(CameraMode.Camera_Capture_Mode,
				new DJIExecuteResultCallback() {
					public void onResult(DJIError mErr) {
						Log.d(TAG, "Set Camera Mode errorCode = "
								+ mErr.errorCode);
						Log.d(TAG, "Set Camera Mode errorDescription = "
								+ mErr.errorDescription);
						String result = "errorCode ="
								+ mErr.errorCode
								+ "\n"
								+ "errorDescription ="
								+ DJIError
										.getErrorDescriptionByErrcode(mErr.errorCode);
						handler.sendMessage(handler.obtainMessage(SHOWTOAST,
								result));
					}
				});
	}

	@Override
	protected void onResume() {
		// activity显示的时候，创建定时器，执行相应的定时任务
		mTimer = new Timer();
		Task task = new Task();
		mTimer.schedule(task, 0, 500);

		super.onResume();
	}

	@Override
	protected void onPause() {
		// activity关闭的时候，关闭定时器和相关的任务
		if (mTimer != null) {
			mTimer.cancel();
			mTimer.purge();
			mTimer = null;
		}

		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// 销毁SurfaceView
		mDjiGLSurfaceView.destroy();
		// 释放相机图像数据缓存
		DJIDrone.getDjiCamera().setReceivedVideoDataCallBack(null);
		// 断开飞行器连接
		DJIDrone.disconnectToDrone();

		super.onDestroy();
	}
}
