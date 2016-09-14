package sunil.worldpeace;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sunil on 9/10/16.
 */
public class RealmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(configuration);
    }
}

