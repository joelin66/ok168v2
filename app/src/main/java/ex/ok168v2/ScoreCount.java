package ex.ok168v2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class ScoreCount extends Activity {

    public static final String TAG = "Score Counter";
    public Button btn_toggle;
    public TextView tmr_value;
    boolean count_toggle = false;
    public CountDownTimerV1 mc;
    public short timer_base =0;
    public short  timer_unit =1000;
    public long  millis,millis_tmp=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_count);
        btn_toggle = (Button) findViewById(R.id.btn_count);
        tmr_value =(TextView) findViewById(R.id.tmr_disply);
        btn_toggle.setOnClickListener(btn_toggle_listener);

        timer_base =3;
         mc = new  CountDownTimerV1((timer_base *timer_unit),timer_unit);
    }
    public OnClickListener btn_toggle_listener = new OnClickListener(){
        public void onClick(View v){

            if( count_toggle == false) {
                btn_toggle.setText(R.string.count_start);
                btn_toggle.setBackgroundColor(Color.GREEN);
                count_toggle =true;
                mc.start();

            }else
            {
                count_toggle =false;
                btn_toggle.setText(R.string.count_stop);
                btn_toggle.setBackgroundColor(Color.RED);
                millis_tmp = millis;
                mc.cancel();

            }

        }
    }; // why is a ; here ? to find it!
    // inital timer
    // CountDownTimer class
    public class CountDownTimerV1 extends CountDownTimer {
        public CountDownTimerV1(long startTime, long interval)
        {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {

            tmr_value.setText("時間到");
            millis_tmp =0;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(millis_tmp ==0 ){
                millis = millisUntilFinished;
                String hms = String.format("%02d:%02d",  TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                tmr_value.setText(hms);
            }
            else
            {
                if(millis_tmp > 0){
                millis_tmp =(millis_tmp-timer_unit);
                String hms = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millis_tmp) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis_tmp)),
                        TimeUnit.MILLISECONDS.toSeconds(millis_tmp) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis_tmp)));
                tmr_value.setText(hms);}

                else{
                    tmr_value.setText("時間到");
                    millis_tmp =0;
                }

            }
        }

    }

// inital timer

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_count, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onStart() {
        super.onStart();
        Log.v(TAG,"onStart");
    }

    public void onResume()
    {
        super.onResume();
        Log.v(TAG,"onResume");
    }

    public void onPause()
    {
        super.onPause();
        Log.v(TAG,"onPause");
    }

    public void onStop()
    {
        super.onStop();
        Log.v(TAG,"onStop");
    }

    public void onRestart()
    {
        super.onRestart();
        Log.v(TAG,"onReStart");
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }
}
