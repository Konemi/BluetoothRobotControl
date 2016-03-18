package iut.valence.tp.bluetoothrobotcontrol;

import android.app.Application;
import android.content.res.Configuration;

import fr.iutvalence.android.BTConnectionHandlerLib.BTConnectionHandler;

/**
 * Created by Jocelyn on 18/03/2016.
 */
public class bluetoothApplication extends Application {

    BTConnectionHandler btConnectionHandler;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public bluetoothApplication() {
        super();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    public BTConnectionHandler getBtConnectionHandler()
    {
        return this.btConnectionHandler;
    }

    public void setBtConnectionHandler(BTConnectionHandler btConnectionHandler)
    {
        this.btConnectionHandler = btConnectionHandler;
    }
}
