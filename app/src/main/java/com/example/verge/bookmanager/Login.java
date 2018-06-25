package com.example.verge.bookmanager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verge.DAO.UserDAO;

public class Login extends AppCompatActivity {

	EditText username;
	EditText password;
	TextView register;
	Button login;
	private ImageView head_imageview;
	private MyReceiver receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		findViews();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(getResources().getColor(R.color.ui_head));
		}

	}
	//自定义广播接收器
	class MyReceiver extends BroadcastReceiver {

		@SuppressLint("WrongConstant")
		@Override
		public void onReceive(Context context, Intent intent) {
			//自定义广播接收器接收到广播后执行的操作
			String key = intent.getStringExtra("key");
			Toast.makeText(getApplicationContext(), "欢迎来到图书管理系统:"+key, 1).show();
		}
	}
	private void findViews() {
		username =  findViewById(R.id.username_edit);
		password =  findViewById(R.id.passwd_edit);
		login =  findViewById(R.id.btn_login);
		register =  findViewById(R.id.newuser_text);
		head_imageview =  findViewById(R.id.head_imageview);

		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = username.getText().toString();
				String pass = password.getText().toString();
				if (name.equals("admin")&&pass.equals("123456")){
					Intent intent = new Intent("com.xqx.mybrodcast");
					intent.putExtra("key", name+"管理员");
					sendBroadcast(intent);
					intent = new Intent(Login.this, AdminActivity.class);
					startActivity(intent);
					finish();
				} else{
					UserDAO uService = new UserDAO(Login.this);
					int flag = uService.login(name, pass);
					if (flag!=-1) {
						//实例化自定义广播接收器对象
						receiver = new MyReceiver();
						//动态注册广播接收器
						registerReceiver(receiver, new IntentFilter("com.xqx.mybrodcast"));

						//发送广播
						Intent intent = new Intent("com.xqx.mybrodcast");
						intent.putExtra("key", name+"用户");
						sendBroadcast(intent);
					/*Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT)
							.show();*/
						intent = new Intent(Login.this, MainActivity.class);
						((BaseApplication)getApplication()).setUserId(flag);
						((BaseApplication)getApplication()).setPassword(pass);
						startActivity(intent);

						// 判断用户名是否为空
					} else if (name.equals("")) {
						DialogDemo.builder(Login.this, "错误信息", "用户名不能为空！");
						// 判断密码是否为空
					} else if (pass.equals("")) {
						DialogDemo.builder(Login.this, "错误信息", "密码不能为空！");
					} // 判断用户名和密码是否正确
					else if (!(name.equals(username) && pass.equals(password))) {
						DialogDemo.builder(Login.this, "错误信息", "用户名或密码错误，请重新输入");
					} else {
						Toast.makeText(Login.this, "请注册后再登陆", Toast.LENGTH_SHORT)
								.show();
					}
				}

			}
		});
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Login.this, Register.class);
				startActivity(intent);
			}
		});
//		head_imageview.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(Login.this, Head.class);
//				startActivity(intent);
//			}
//		});
	}

	// ============================================退出============================================

	// 因为Activity继承了TabActivity,所以不能监听到onKeyDown()方法，重写以下方法
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getRepeatCount() == 0) {
			// exitBy2Click(); //调用双击退出函数
			exit();
		} else {
			return super.dispatchKeyEvent(event);
		}
		return false;
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			isExit = false;
		}
		
	};
	private boolean isExit;

	public void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(Login.this, "再按一次退出我的应用", Toast.LENGTH_SHORT).show();
			handler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
		}
	}
}
