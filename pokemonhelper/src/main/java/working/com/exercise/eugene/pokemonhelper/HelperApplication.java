package working.com.exercise.eugene.pokemonhelper;

import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.database.Database;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import working.com.exercise.eugene.pokemonhelper.client.AdapterType;
import working.com.exercise.eugene.pokemonhelper.client.PokemonService;
import working.com.exercise.eugene.pokemonhelper.db.DaoMaster;
import working.com.exercise.eugene.pokemonhelper.db.DaoSession;


public class HelperApplication extends Application {
    private static final String DB_NAME = "pokemon-data";

    private PokemonService pokemonService;
    private Scheduler defaultSubscribeScheduler;

    private DaoSession daoSession;

    private static HelperApplication get(Context context) {
        return (HelperApplication) context.getApplicationContext();
    }

    public static HelperApplication create(Context context) {
        return HelperApplication.get(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), DB_NAME);
        Database database = devOpenHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }


    /**
     * Pokemon API
     *
     * @return {@link working.com.exercise.eugene.pokemonhelper.client.PokemonService }
     */
    public PokemonService getPokemonService(AdapterType adapterType) {
        if (pokemonService == null) {
            pokemonService = PokemonService.Factory.create(adapterType);
        }
        return pokemonService;
    }

    // “work” on io thread
    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    /**
     * @return Database session
     * Required to handle data transactions.
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
