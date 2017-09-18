package working.com.exercise.eugene.pokemonhelper.client;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import working.com.exercise.eugene.pokemonhelper.model.Pokedex;

public interface PokemonService {

    /**
     * @return pokemon pokedex RX
     */
    @GET("pokedex/2")
    Observable<Pokedex> pokedex();


    /**
     * @return pokemon pokedex
     */
    @GET("pokedex/2")
    Call<Pokedex> getPokedex();


    class Factory {
        public static final String END_POINT = "http://pokeapi.co/api/v2/";
        public static final Integer TIMEOUT = 30;

        public static PokemonService create(AdapterType adapterType) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(END_POINT);
            builder.addConverterFactory(GsonConverterFactory.create());
            if (adapterType == AdapterType.RX) {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            }
            builder.client(http3Client());
            builder.build();
            return builder.build().create(PokemonService.class);
        }

        /**
         * Timeout
         * Logging Interceptor
         *
         * @return HTTP3 Client
         */
        private static OkHttpClient http3Client() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
//            if (BuildConfig.DEBUG) {
//                builder.addInterceptor(loggingInterceptor());
//            }
            // Typically only show logs while in DEBUG
            builder.addInterceptor(loggingInterceptor());
            return builder.build();
        }

        /**
         * Log the body of the responses
         *
         * @return Logging Interceptor
         */
        private static HttpLoggingInterceptor loggingInterceptor() {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            return logging;
        }
    }
}
