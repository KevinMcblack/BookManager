package com.example.verge.bookmanager;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.verge.DAO.UserDAO;
import com.example.verge.model.User;

public class MyFragment extends Fragment {
    TextView name;
    TextView userId;
    TextView phone;
    TextView area;
    TextView sex;
    Button editInfo;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myLayout = inflater.inflate(R.layout.layout_my_frame,container,false);
        name = myLayout.findViewById(R.id.name);
        userId = myLayout.findViewById(R.id.userId);
        phone = myLayout.findViewById(R.id.phone);
        area = myLayout.findViewById(R.id.area);
        sex = myLayout.findViewById(R.id.sex);
        editInfo = myLayout.findViewById(R.id.editInfo);
        initInfo();
        myLayout.findViewById(R.id.weather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),EditUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
            }
        });
        return myLayout;
    }
    public void initInfo(){
        UserDAO dao = new UserDAO(getContext());
        user = dao.queryUser(((BaseApplication)getActivity().getApplication()).getUserId());
        if(user!=null){
            name.setText(user.getUsername() );
            userId.setText(String.format("%s%s", userId.getText(), String.valueOf(user.getId())));
            phone.setText(String.format("%s%s", phone.getText(), user.getPhone()));
            area.setText(String.format("%s%s", area.getText(), user.getArea()));
            sex.setText(String.format("%s%s", sex.getText(), user.getSex()));
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                userId.setText("会员编号：");
                phone.setText("电话号码");
                area.setText("地区：");
                sex.setText("性别：");
                initInfo();
                break;
                default:
                    break;
        }
    }
}
