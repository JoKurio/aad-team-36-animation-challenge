package io.github.jokurio.aqa;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class AndroidQuiz extends AppCompatActivity {
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
    }
    public void drivers(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);
        image.startAnimation(animation);
        imag.startAnimation(animation);
    }
    public void intent(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img3);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        image.startAnimation(animation);
        imag.startAnimation(animation);
    }
    public void ANR(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img4);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade);
        image.startAnimation(animation);
        imag.startAnimation(animation);
    }
    public void APK(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img5);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation);
        image.startAnimation(animation);
        imag.startAnimation(animation);
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
    }

    //animates the 'bluetooth' option, so it's animation is different from that of the wifi
    public void wifi(View view){
        ImageView imag = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.img2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation);
        image.startAnimation(animation);
        imag.startAnimation(animation);
    }
    public void submitQuiz(View view) {
        //Animates the image at the top
        ImageView image = findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
        //calculates the final score
        int finalScore;
        int quizOne = 0;
        int quizTwo = 0;
        int quizThree = 0;
        int quizFour = 0;
        int quizFive = 0;
        int quizSix = 0;
        Boolean Linux;
        Boolean Windows;
        linux = findViewById(R.id.linux);
        windows = findViewById(R.id.windows);
        Linux = linux.isChecked();
        Windows = windows.isChecked();
        if (Linux) {
            quizOne = 5;
        } else if (Windows) {
            quizOne = 0;
        }
        Boolean Bluetooth;
        Boolean Wifi;
        bluetooth = findViewById(R.id.bluetooth);
        wifi = findViewById(R.id.wifi);
        Bluetooth = bluetooth.isChecked();
        Wifi = wifi.isChecked();
        if (Bluetooth) {
            quizTwo = 0;
        } else if (Wifi) {
            quizTwo = 5;
        }
        Boolean Implicit;
        Boolean Explicit;
        implicit = findViewById(R.id.implicit);
        explicit = findViewById(R.id.explicit);
        Implicit = implicit.isChecked();
        Explicit = explicit.isChecked();
        if (Implicit) {
            quizThree = 5;
        } else if (Explicit) {
            quizThree = 0;
        }
        Boolean ANR_ONE;
        Boolean ANR_TWO;
        ANR1 = findViewById(R.id.anr1);
        ANR2 = findViewById(R.id.anr2);
        ANR_ONE = ANR1.isChecked();
        ANR_TWO = ANR2.isChecked();
        if (ANR_ONE) {
            quizFour = 0;
        } else if (ANR_TWO) {
            quizFour = 5;
        }
        Boolean Processing;
        Boolean Packaging;
        APK1 = findViewById(R.id.apk1);
        APK2 = findViewById(R.id.apk2);
        Processing = APK1.isChecked();
        Packaging = APK2.isChecked();
        if (Processing) {
            quizFive = 0;
        }else if (Packaging) {
            quizFive = 5;
        }
        Boolean Andela;
        Boolean Adult;
        ALC1 = findViewById(R.id.alc1);
        ALC2 = findViewById(R.id.alc2);
        Andela = ALC1.isChecked();
        Adult = ALC2.isChecked();
        if (Andela) {
            quizSix = 5;
        }
        if (Adult) {
            quizSix = 0;
        }

        finalScore = (quizOne + quizTwo + quizThree + quizFour + quizFive + quizSix);
        //Sends a toast message depending on the score
        if (finalScore == 30) {
            Toast.makeText(this, "Perfect!You got all 6 questions right, " + finalScore + " points for you.", Toast.LENGTH_LONG).show();
        } else if (finalScore == 25) {
            Toast.makeText(this, "Very good!You answered 5 questions correctly, " + finalScore + " points for you.", Toast.LENGTH_LONG).show();
        } else if (finalScore == 20) {
            Toast.makeText(this, "Good!You got 4 questions right, " + finalScore + " points for you.", Toast.LENGTH_LONG).show();
        } else if (finalScore == 15) {
            Toast.makeText(this, "Okay, you got 3 questions right, " + finalScore + " points for you", Toast.LENGTH_LONG).show();
        } else if (finalScore == 10) {
            Toast.makeText(this, "Just " + finalScore + " points out of 30. You can do better.", Toast.LENGTH_LONG).show();
        } else if (finalScore == 5) {
            Toast.makeText(this, +finalScore + " points out of 30. Not too good.", Toast.LENGTH_LONG).show();
        } else if (finalScore == 0) {
            Toast.makeText(this, +finalScore + " points out of 30. Well, you can always try again", Toast.LENGTH_LONG).show();
        }
    }
    //This method restarts the quiz by resetting all the values
    public void resetQuiz(View v) {
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

}
