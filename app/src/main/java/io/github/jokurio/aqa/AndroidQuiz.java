package io.github.jokurio.aqa;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidQuiz extends AppCompatActivity {
    public static int finalScore;
    RadioButton linux;
    RadioButton windows;
    RadioButton bluetooth;
    RadioButton wifi;
    RadioButton implicit;
    RadioButton explicit;
    RadioButton ANR1;
    RadioButton ANR2;
    RadioButton APK1;
    RadioButton APK2;
    RadioButton ALC1;
    RadioButton ALC2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_quiz);
    }
    /**The following 6 methods(kernel, drivers, intent,ANR, APK and ALC)  animates
     * the images besides each question as well as the image at the top of the screen
     */
    public void kernel(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img1);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        image.startAnimation(animation);
        imag.startAnimation(animation);

        zoomSelection(view);
    }
    public void drivers(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);
        image.startAnimation(animation);
        imag.startAnimation(animation);

        zoomSelection(view);
    }
    public void intent(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img3);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        image.startAnimation(animation);
        imag.startAnimation(animation);

        zoomSelection(view);
    }
    public void ANR(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img4);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade);
        image.startAnimation(animation);
        imag.startAnimation(animation);

        zoomSelection(view);
    }
    public void APK(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img5);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation);
        image.startAnimation(animation);
        imag.startAnimation(animation);

        zoomSelection(view);
    }
    public void ALC(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img6);
        ImageView alc = findViewById(R.id.alcImage);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        image.startAnimation(animation);
        imag.startAnimation(animation);
        alc.startAnimation(animation);

        zoomSelection(view);
    }

    //animates the 'bluetooth' option, so it's animation is different from that of the wifi
    public void wifi(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation);
        image.startAnimation(animation);
        imag.startAnimation(animation);

        zoomSelection(view);
    }
    public void submitQuiz(View view) {
        //Animates the image at the top
        ImageView image = findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
    }
    /**public void submitQuiz(View view) {

        int quizOne = 0;
        int quizTwo = 0;
        int quizThree = 0;
        int quizFour = 0;
        int quizFive = 0;
        int quizSix = 0;
        Boolean Linux;
        linux = findViewById(R.id.linux);
        Linux = linux.isChecked();
        if (Linux) {
            quizOne = 5;
        }

        Boolean Wifi;
        wifi = findViewById(R.id.wifi);
        Wifi = wifi.isChecked();
        if (Wifi) {
            quizTwo = 5;
        }

        Boolean Implicit;
        implicit = findViewById(R.id.implicit);
        Implicit = implicit.isChecked();
        if (Implicit) {
            quizThree = 5;
        }

        Boolean ANR_TWO;
        ANR2 = findViewById(R.id.anr2);
        ANR_TWO = ANR2.isChecked();
        if (ANR_TWO) {
            quizFour = 5;
        }

        Boolean Packaging;
        APK2 = findViewById(R.id.apk2);
        Packaging = APK2.isChecked();
        if (Packaging) {
            quizFive = 5;
        }

        Boolean Andela;
        ALC1 = findViewById(R.id.alc1);
        Andela = ALC1.isChecked();
        if (Andela) {
            quizSix = 5;
        }*/
        finalScore = (quizOne + quizTwo + quizThree + quizFour + quizFive + quizSix);

        Intent intent = new Intent(AndroidQuiz.this, ScoreActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
    //This method restarts the quiz by resetting all the values
    public void resetQuiz(View v) {
        linux = findViewById(R.id.linux);
        windows = findViewById(R.id.windows);
        bluetooth = findViewById(R.id.bluetooth);
        wifi = findViewById(R.id.wifi);
        implicit = findViewById(R.id.implicit);
        explicit = findViewById(R.id.explicit);
        ANR1 = findViewById(R.id.anr1);
        ANR2 = findViewById(R.id.anr2);
        APK1 = findViewById(R.id.apk1);
        APK2 = findViewById(R.id.apk2);
        ALC1 = findViewById(R.id.alc1);
        ALC2 = findViewById(R.id.alc2);

        linux.setChecked(false);
        windows.setChecked(false);
        bluetooth.setChecked(false);
        wifi.setChecked(false);
        implicit.setChecked(false);
        explicit.setChecked(false);
        ALC2.setChecked(false);
        ALC1.setChecked(false);
        APK2.setChecked(false);
        APK1.setChecked(false);
        ANR2.setChecked(false);
        ANR1.setChecked(false);

        //This rotates the image while the quiz is being restarted
        ImageView image = findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);
        image.startAnimation(animation1);
    }

    // helper function to zoom selected radiobuttons
    private void zoomSelection(View view){
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.zoom);
        animator.setTarget(view);
        animator.start();
    }
}

