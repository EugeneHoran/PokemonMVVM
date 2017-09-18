package working.com.exercise.eugene.pokemonhelper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pokedex {

    @SerializedName("pokemon_entries")
    @Expose
    private List<PokemonEntries> pokemonEntries = new ArrayList<>();

    public List<PokemonEntries> getPokemonEntries() {
        return pokemonEntries;
    }
}
