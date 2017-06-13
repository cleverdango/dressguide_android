package cn.chengwenjun.dressgudie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import cn.chengwenjun.dressgudie.Dao.UserDao;
import cn.chengwenjun.dressgudie.bean.User;

public class RegisterActivity extends AppCompatActivity {
    String email;
    String password;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDao = new UserDao(this);

        Button bt_register = (Button) findViewById(R.id.email_register_button);
        Button bt_cannel = (Button) findViewById(R.id.email_cancel_button);


        final EditText et_register_email = (EditText) findViewById(R.id.et_register_email);
        final EditText et_register_username = (EditText) findViewById(R.id.et_register_username);
        final EditText et_register_password = (EditText) findViewById(R.id.et_register_password);
        final EditText et_register_review = (EditText) findViewById(R.id.et_register_reviewpassword);


        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //username = et_register_username.getText().toString().trim();
                email = et_register_email.getText().toString().trim();
                password = et_register_password.getText().toString();
                String reiviewStr = et_register_review.getText().toString();
                String username = et_register_username.getText().toString();

                if (userDao.queryByEmail(email) != null) {
                    Toast.makeText(getApplicationContext(), "该邮箱已被注册", Toast.LENGTH_LONG).show();
                } else if (email.length() == 0) {
                    Toast.makeText(getApplicationContext(), "邮箱不能为空", Toast.LENGTH_LONG).show();
                } else if (password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_LONG).show();
                } else if (!password.equals(reiviewStr)) {
                    Toast.makeText(getApplicationContext(), "两次密码不一致，请重试", Toast.LENGTH_LONG).show();
                } else {
                    //firstr 为性别 newstr为hobby
                    userDao.insert(new User(email, password, username));
                    finish();

                }

            }
        });
        bt_cannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}



