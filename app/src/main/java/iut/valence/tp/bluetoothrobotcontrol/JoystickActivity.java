package iut.valence.tp.bluetoothrobotcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import fr.iutvalence.android.BTConnectionHandlerLib.BTConnectionHandler;
import fr.iutvalence.android.BTConnectionHandlerLib.JoystickView;

/**
 * Created by Jocelyn on 18/03/2016.
 */
public class JoystickActivity extends AppCompatActivity {

    bluetoothApplication application;

    BTConnectionHandler btConnectionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);

        this.application = (bluetoothApplication) getApplication();
        this.btConnectionHandler = this.application.getBtConnectionHandler();
        this.createJoystickControl();
    }

    private void createJoystickControl()
    {
        JoystickView joystickView = new JoystickView(this);
        setContentView(joystickView);
        JoystickView.ValueChangedHandler handler = new JoystickView.ValueChangedHandler() {
            @Override
            public void onValueChanged(int Vg, int Vd)
            {
                if(Vg == 0 && Vd ==0)
                {
                    btConnectionHandler.sendBtData(JoystickActivity.this, "s");
                }
                if((Vd-Vg<10 || Vg-Vd<10) && Vd>0 && Vg >0)
                {
                    btConnectionHandler.sendBtData(JoystickActivity.this, "a");
                }
                if((Vd-Vg<10 || Vg-Vd<10) && Vd<0 && Vg<0)
                {
                    btConnectionHandler.sendBtData(JoystickActivity.this, "r");
                }
                if(Vd>Vg && Vd-Vg>10)
                {
                    btConnectionHandler.sendBtData(JoystickActivity.this, "g");
                }
                if(Vd<Vg && Vg-Vd>10)
                {
                    btConnectionHandler.sendBtData(JoystickActivity.this, "d");
                }
            }
        };
        joystickView.setValueChangeHandler(handler);
    }
}
