package live.page.chen.justeprix;

import androidx.appcompat.app.ActionBar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import live.page.chen.justeprix.utils.GrandMereActivity;

public class GameActivity extends GrandMereActivity {

    private final int maxNumber = 100 + ((int) Math.floor(Math.random() * 200));
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(maxNumber);
        int best = Data.getBest(this, Integer.MAX_VALUE);
        Data.setBest(this, 1000);

        int randomNumber = (int) Math.floor(Math.random() * maxNumber);
        System.out.println(randomNumber);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        chronometer = findViewById(R.id.chronometer);


        chronometer.start();


        TextView title = findViewById(R.id.title);
        title.setText(getString(R.string.nombre_info, maxNumber));
        EditText number = findViewById(R.id.Number);
        Button submit = findViewById(R.id.EnterButton);
        TextView textb = findViewById(R.id.actionsB);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        textb.setText("");

        @SuppressLint("SetTextI18n") Runnable action = () -> {
            textb.setText("");
            new Handler().postDelayed(() -> {
                textb.setScaleX(0.3F);
                textb.setScaleY(0.3F);
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator.ofFloat(textb,
                        View.SCALE_X, 1))
                        .with(ObjectAnimator
                                .ofFloat(textb,
                                        View.SCALE_Y, 1));
                set.setDuration(500);
                set.setInterpolator(new DecelerateInterpolator());

                set.start();
                int num = 0;
                boolean stop;
                try {
                    num = Integer.parseInt(number.getText().toString());
                    stop = false;
                } catch (NumberFormatException e) {
                    textb.setText(R.string.number_enter);
                    stop = true;
                }
                if (!stop) {
                    if (num == randomNumber) {
                        textb.setText(R.string.number_found);
                        chronometer.stop();
                        int s = (int) (((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000));
                        if (s < best) {
                            textb.setText(getString(R.string.number_found) + getString(R.string.new_record) + s + " second.");
                            Data.setBest(this, s);
                        }
                        return;
                    }
                    number.setText("");
                    number.findFocus();
                    if (num < randomNumber) {
                        textb.setText(R.string.number_greater);
                    } else {
                        textb.setText(R.string.number_lower);
                    }
                }

            }, 600);
        };
        number.setOnKeyListener((view, keyCode, keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                action.run();
                return true;
            }
            return false;
        });
        submit.setOnClickListener(view -> action.run());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
}