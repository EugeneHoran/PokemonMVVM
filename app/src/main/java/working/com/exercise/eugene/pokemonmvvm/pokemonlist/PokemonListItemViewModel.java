package working.com.exercise.eugene.pokemonmvvm.pokemonlist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import working.com.exercise.eugene.pokemonhelper.db.PokedexData;

public class PokemonListItemViewModel extends BaseObservable {
    public ObservableField<PokedexData> pokemonObservable = new ObservableField<>();
    @Nullable
    private WeakReference<PokemonItemNavigator> mNavigator;

    public PokemonListItemViewModel() {
    }

    public void setPokemon(PokedexData pokemon) {
        pokemonObservable.set(pokemon);
    }

    public void setNavigator(PokemonItemNavigator navigator) {
        mNavigator = new WeakReference<>(navigator);
    }

    @Bindable
    public String getPokemonName() {
        if (pokemonObservable.get() == null) {
            return "No data";
        }
        return pokemonObservable.get().getName();
    }

    public long getPokemonId() {
        return pokemonObservable.get().getId();
    }

    public void pokemonClicked() {
        long taskId = getPokemonId();
        if (mNavigator != null && mNavigator.get() != null) {
            mNavigator.get().openPokemonDetails(taskId);
        }
    }
}
