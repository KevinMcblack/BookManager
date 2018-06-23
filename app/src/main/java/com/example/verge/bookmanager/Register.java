package com.example.verge.bookmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verge.DAO.UserDAO;
import com.example.verge.model.User;

public class Register extends Activity {

	EditText username_register;
	EditText passwdtext_register;
	EditText sex;
	EditText phone;
	EditText area;
	Button btn_register;
	Button btn_back;
	TextView setting_about_back;

	private void findViews() {
		username_register = findViewById(R.id.username_register);
		passwdtext_register =  findViewById(R.id.passwdtext_register);
		btn_register = findViewById(R.id.btn_register);
		btn_back =  findViewById(R.id.btn_back);
		setting_about_back = findViewById(R.id.setting_about_back);
		sex = findViewById(R.id.registerSex);
		phone = findViewById(R.id.registerPhone);
		area = findViewById(R.id.registerArea);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		findViews();

		btn_register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = username_register.getText().toString().trim();
				String pass = passwdtext_register.getText().toString().trim();
				String rSex = sex.getText().toString();
				String rPhone = phone.getText().toString();
				String rArea = area.getText().toString();
				if (name.equals("")) {
					DialogDemo.builder(Register.this, "错误信息", "用户名不能为空！");
				} else if (pass.equals("")) {
					DialogDemo.builder(Register.this, "错误信息", "密码不能为空！");
				} else if (rSex.equals("")){
					DialogDemo.builder(Register.this, "错误信息", "性别不能为空！");
				} else if (rPhone.equals("")){
					DialogDemo.builder(Register.this, "错误信息", "电话号码不能为空！");
				} else if (rArea.equals("")){
					DialogDemo.builder(Register.this, "错误信息", "地区不能为空！");
				} else {
					UserDAO uService = new UserDAO(Register.this);
					User user = new User();
					user.setUsername(name);
					user.setPassword(pass);
					user.setPhone(rPhone);
					user.setSex(rSex);
					user.setArea(rArea);
					uService.register(user);
					Toast.makeText(Register.this, "注册帐号成功", Toast.LENGTH_LONG)
							.show();
					finish();
				}
			}
		});
		btn_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		setting_about_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
