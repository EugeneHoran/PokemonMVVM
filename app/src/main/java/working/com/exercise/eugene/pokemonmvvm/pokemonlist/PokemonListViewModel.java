package working.com.exercise.eugene.pokemonmvvm.pokemonlist;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import working.com.exercise.eugene.pokemonhelper.client.AdapterType;
import working.com.exercise.eugene.pokemonhelper.client.PokemonService;
import working.com.exercise.eugene.pokemonhelper.db.PokedexData;
import working.com.exercise.eugene.pokemonhelper.model.Pokedex;
import working.com.exercise.eugene.pokemonmvvm.PokemonApplication;

public class PokemonListViewModel extends BaseObservable {
    public Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Pokedex pokedex;

    public ObservableInt pokemonProgress = new ObservableInt(View.VISIBLE);
    public ObservableInt pokemonRecycler = new ObservableInt(View.GONE);
    public ObservableList<PokedexData> pokemonDataList = new ObservableArrayList<>();

    public PokemonListViewModel(@NonNull Context context) {
        this.context = context;
    }

    public void loadPokemon() {
        pokemonProgress.set(View.VISIBLE);
        pokemonRecycler.set(View.GONE);
        PokemonApplication pokemonApplication = (PokemonApplication) PokemonApplication.create(context.getApplicationContext());
        PokemonService pokemonService = pokemonApplication.getPokemonService(AdapterType.RX);
        Disposable disposable = pokemonService.pokedex()
                .subscribeOn(pokemonApplication.defaultSubscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Pokedex>() {
                    @Override
                    public void onNext(@NonNull Pokedex pokedex) {
                        PokemonListViewModel.this.pokedex = pokedex;
                    }

                    @Override
                    public void onComplete() {
                        Pokedex pokedex = PokemonListViewModel.this.pokedex;
                        List<PokedexData> mPokemonList = new ArrayList<>();
                        PokedexData pokemonItem;
                        for (int i = 0; i < pokedex.getPokemonEntries().size(); i++) {
                            pokemonItem = new PokedexData();
                            pokemonItem.setId(Long.valueOf(pokedex.getPokemonEntries().get(i).getPokemonNumber()));
                            pokemonItem.setName(pokedex.getPokemonEntries().get(i).getPokemon().getPokemonName());
                            pokemonItem.setPokemonNumber(pokedex.getPokemonEntries().get(i).getPokemonNumber());
                            pokemonItem.setUrl(pokedex.getPokemonEntries().get(i).getPokemon().getPokemonUrl());
                            mPokemonList.add(pokemonItem);
                        }
                        pokemonDataList.clear();
                        pokemonDataList.addAll(mPokemonList);
                        pokemonProgress.set(View.GONE);
                        pokemonRecycler.set(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
        compositeDisposable.add(disposable);
    }

    public void clearSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
