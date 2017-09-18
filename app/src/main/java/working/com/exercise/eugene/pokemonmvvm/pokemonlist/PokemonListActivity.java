package working.com.exercise.eugene.pokemonmvvm.pokemonlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import working.com.exercise.eugene.pokemonmvvm.R;

public class PokemonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        PokemonListFragment pokemonListFragment = (PokemonListFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (pokemonListFragment == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, PokemonListFragment.newInstance()).commit();
        }
    }
}
