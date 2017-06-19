package cn.chengwenjun.dressguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.chengwenjun.dressguide.Dao.UserDao;
import cn.chengwenjun.dressguide.bean.User;

public class UserInfoActivity extends AppCompatActivity {
    User user;
    UserDao userDao = new UserDao(this);
    protected static final String PREFS_NAME = "myPrefsFile";
    private SharedPreferences sp;
    private boolean remeberUser;
    Button bt_user_update;
    Button bt_cancel_button;
    EditText et_update_email;
    EditText et_update_username;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        bt_user_update = (Button) findViewById(R.id.bt_user_update);
        bt_cancel_button = (Button) findViewById(R.id.bt_cancel_update);
        et_update_email = (EditText) findViewById(R.id.et_update_email);
        et_update_username = (EditText) findViewById(R.id.et_update_username);




        //String Inemail = intent.getStringExtra("email");
        sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String oldEmail = sp.getString("email", "");
        user = userDao.queryByEmail(oldEmail);
        et_update_email.setText(user.getEmail());
        et_update_username.setText(user.getName());


        bt_user_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = et_update_username.getText().toString();
                String email = et_update_email.getText().toString();

                //更新sp中的email
                remeberUser = sp.getBoolean("remeber", false);
                if (remeberUser) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email", email);
                    editor.commit();
                }

                //更新数据库中的email和username
                userDao.update(new User(email, user.getPassword(), username));
                intent = new Intent(UserInfoActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });
        bt_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
