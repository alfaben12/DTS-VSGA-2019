package polinema.ac.id.dtschapter03_starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity {

    // Deklarasi variabel editTextPassword dengan tipe EditText
    EditText editTextCode;
    // Deklarasi variabel editTextEmail dengan tipe EditText
    EditText editTextPassword;
    // Deklarasi variabel editTextPassword dengan tipe EditText
    EditText editTextConfirmPassword;

    String code;
    String password;
    String confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Binding edt_txt_password ke variabel editTextPassword
        editTextCode = findViewById(R.id.edt_txt_code);
        // Binding edt_txt_password ke variabel editTextPassword
        editTextPassword = findViewById(R.id.edt_txt_password);
        // Binding edt_confirm_password ke variabel editTextConfirmPassword
        editTextConfirmPassword = findViewById(R.id.edt_confirm_password);
    }

    public void postChangePassword(View view) {
        code = editTextCode.getText().toString();
        password = editTextPassword.getText().toString();
        confirm_password = editTextConfirmPassword.getText().toString();

        // code
        if(TextUtils.isEmpty(code)){
            Toast.makeText(view.getContext(), "Code tidak boleh kosong!", Toast.LENGTH_LONG).show();
        }
        // password
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(view.getContext(), "Password tidak boleh kosong!", Toast.LENGTH_LONG).show();
        }
        // confirm password
        else if(TextUtils.isEmpty(confirm_password)){
            Toast.makeText(view.getContext(), "Confirm password tidak boleh kosong!", Toast.LENGTH_LONG).show();
        }
        // validasi password & confirm password harus sama
        else if(!password.equals(confirm_password)){
            Toast.makeText(view.getContext(), "Password dan confirm password tidak sama!", Toast.LENGTH_LONG).show();
        }
        else{
            Intent i = new Intent(ResetPassword.this, Success.class);
            startActivity(i);
        }
    }
}
