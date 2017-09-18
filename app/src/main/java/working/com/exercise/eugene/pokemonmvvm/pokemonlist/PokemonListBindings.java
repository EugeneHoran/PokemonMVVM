package working.com.exercise.eugene.pokemonmvvm.pokemonlist;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import working.com.exercise.eugene.pokemonhelper.db.PokedexData;

public class PokemonListBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:pokemonDataList")
    public static void setItems(RecyclerView recyclerView, List<PokedexData> pokedexDataList) {
        PokemonListAdapter adapter = (PokemonListAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setPokemonItems(pokedexDataList);
        }
    }
}
