package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verge.DAO.UserDAO;
import com.example.verge.model.User;

public class EditUserActivity extends AppCompatActivity {
    TextView username;
    EditText newPassword;
    EditText repeatPassword;
    EditText phone;
    EditText area;
    RadioGroup sex;
    RadioButton male;
    RadioButton female;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Intent intent = getIntent();
        username = findViewById(R.id.usernameText);
        newPassword = findViewById(R.id.newPasswordEdit);
        repeatPassword = findViewById(R.id.repeatPasswordEdit);
        phone = findViewById(R.id.phoneEdit);
        area = findViewById(R.id.areaEdit);
        sex = findViewById(R.id.sexGroup);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        submit = findViewById(R.id.submit);
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        final User user = (User)bundle.getSerializable("user");
        if (user!=null){
            username.setText(user.getUsername());
            phone.setText(user.getPhone());
            area.setText(user.getArea());
            String s= user.getSex();
            if(s.equals("男")){
                male.setChecked(true);
            } else {
                female.setChecked(true);
            }
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = newPassword.getText().toString();
                String repeat = repeatPassword.getText().toString();
                String number = phone.getText().toString();
                String address = area.getText().toString();
                if(!address.equals("")){
                    if(!number.equals("")){
                        UserDAO userDAO = new UserDAO(EditUserActivity.this);
                        User user1 = new User();
                        user1.setId(((BaseApplication)getApplication()).getUserId());
                        user1.setUsername(username.getText().toString());
                        user1.setPhone(number);
                        user1.setArea(address);
                        if(sex.getCheckedRadioButtonId()==male.getId()){
                            user1.setSex(male.getText().toString());
                        }else{
                            user1.setSex(female.getText().toString());
                        }
                        if (!password.equals("")){
                            if (password.equals(repeat)){
                                user1.setPassword(password);
                                userDAO.update(user1);
                                Toast.makeText(EditUserActivity.this,"更新完成",Toast.LENGTH_SHORT).show();
                                EditUserActivity.this.setResult(1);
                                Log.i("修改完成","start finish----------");
                                EditUserActivity.this.finish();
                            } else{
                                DialogDemo.builder(EditUserActivity.this,"错误信息","密码不一致");
                            }
                        } else{
                            password = ((BaseApplication)getApplication()).getPassword();
                            user1.setPassword(password);
                            userDAO.update(user1);
                            Toast.makeText(EditUserActivity.this,"更新完成",Toast.LENGTH_SHORT).show();
                            EditUserActivity.this.setResult(1);
                            Log.i("修改完成","start finish----------");
                            EditUserActivity.this.finish();
                        }
                    } else {
                        DialogDemo.builder(EditUserActivity.this,"错误信息","电话号码不能为空");
                    }
                } else {
                    DialogDemo.builder(EditUserActivity.this,"错误信息","地区不能为空");
                }

            }
        });
    }
}
