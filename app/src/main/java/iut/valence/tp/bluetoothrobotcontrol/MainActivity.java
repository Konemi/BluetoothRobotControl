package iut.valence.tp.bluetoothrobotcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import fr.iutvalence.android.BTConnectionHandlerLib.BTConnectionHandler;
import fr.iutvalence.android.BTConnectionHandlerLib.exceptions.BTHandlingException;

public class MainActivity extends AppCompatActivity {

    TextView command, deviceName, status;
    Button send, connect, disconnect, buttonLeft, buttonRight, buttonFront, buttonBack, buttonStop, modeJoystick;
    BTConnectionHandler btConnectionHandler;
    bluetoothApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.application = (bluetoothApplication) getApplication();

        this.command = (TextView) findViewById(R.id.commandText);
        this.deviceName = (TextView) findViewById(R.id.deviceTextName);
        this.status = (TextView) findViewById(R.id.currentStatus);

        this.send = (Button) findViewById(R.id.sendButton);
        this.connect = (Button) findViewById(R.id.connect);
        this.disconnect = (Button) findViewById(R.id.dc);

        this.buttonLeft = (Button) findViewById(R.id.buttonLeft);
        this.buttonRight = (Button) findViewById(R.id.buttonRight);
        this.buttonFront = (Button) findViewById(R.id.buttonFront);
        this.buttonBack = (Button) findViewById(R.id.buttonBack);
        this.buttonStop = (Button) findViewById(R.id.buttonStop);

        this.modeJoystick = (Button) findViewById(R.id.modeJoystick);

        this.application.setBtConnectionHandler(new BTConnectionHandler(this.getBaseContext()));
        this.btConnectionHandler = this.application.getBtConnectionHandler();
    }

    public void mainActivityClickListener(View view){
        switch (view.getId())
        {
            case R.id.sendButton :
                String commande = this.command.getText().toString();
                this.btConnectionHandler.sendBtData(this, commande);
                break;
            case R.id.connect :
                String stringDeviceName = this.deviceName.getText().toString();
                try {
                    this.btConnectionHandler.connectToBTDevice(stringDeviceName);
                    this.status.setText("Status: Connected");
                } catch (BTHandlingException e) {
                    Toast.makeText(this, "Failed to connect to " + stringDeviceName + ".", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.buttonLeft :
                String left = "g";
                this.btConnectionHandler.sendBtData(this, left);
                break;
            case R.id.buttonRight :
                String right = "d";
                this.btConnectionHandler.sendBtData(this, right);
                break;
            case R.id.buttonFront :
                String front = "a";
                this.btConnectionHandler.sendBtData(this, front);
                break;
            case R.id.buttonBack :
                String back = "r";
                this.btConnectionHandler.sendBtData(this, back);
                break;
            case R.id.buttonStop :
                String stop = "s";
                this.btConnectionHandler.sendBtData(this, stop);
                break;
            case R.id.modeJoystick :
                this.startJoystickActivity();
                break;
            default :
        }
    }

    private void startJoystickActivity()
    {
        this.startActivity(new Intent(this, JoystickActivity.class));
    }

}
