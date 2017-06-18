package cn.chengwenjun.dressgudie;

import android.content.Intent;
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

                email = et_register_email.getText().toString().trim();
                password = et_register_password.getText().toString();
                String reiviewStr = et_register_review.getText().toString();
                String username = et_register_username.getText().toString();

                boolean cancel = false;
                View focusView = null;
                et_register_email.setError(null);
                et_register_password.setError(null);
                et_register_review.setError(null);

                if (userDao.queryByEmail(email) != null) {
                    et_register_email.setError(getString(R.string.error_registered_email));
                    focusView = et_register_email;
                    cancel = true;
                } else if (email.length() == 0) {
                    et_register_email.setError(getString(R.string.error_field_required));
                    focusView = et_register_email;
                    cancel = true;

                } else if (!email.contains("@")) {
                    et_register_email.setError(getString(R.string.error_invalid_email));
                    focusView = et_register_email;
                    cancel = true;
                } else if (password.length() == 0) {
                    et_register_password.setError(getString(R.string.error_field_required));
                    focusView = et_register_password;
                    cancel = true;
                } else if (!password.equals(reiviewStr)) {
                    et_register_review.setError(getString(R.string.error_differet_password));
                    focusView = et_register_review;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    userDao.insert(new User(email, password, username));
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
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



