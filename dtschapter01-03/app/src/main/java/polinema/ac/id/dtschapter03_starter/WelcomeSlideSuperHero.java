package polinema.ac.id.dtschapter03_starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeSlideSuperHero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_slide_super_hero);
    }

    public void clickGetStarted(View view) {
        Intent i = new Intent(WelcomeSlideSuperHero.this, WelcomeSlideAssign.class);
        startActivity(i);
    }

    public void clickLogin(View view) {
        Intent i = new Intent(WelcomeSlideSuperHero.this, WelcomeBack.class);
        startActivity(i);
    }
}
