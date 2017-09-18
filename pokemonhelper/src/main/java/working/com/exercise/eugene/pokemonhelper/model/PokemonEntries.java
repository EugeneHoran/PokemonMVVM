package working.com.exercise.eugene.pokemonhelper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonEntries {

    @SerializedName("entry_number")
    @Expose
    private String pokemonNumber;

    @SerializedName("pokemon_species")
    @Expose
    private PokemonSpecies pokemon;

    public String getPokemonNumber() {
        return pokemonNumber;
    }

    public PokemonSpecies getPokemon() {
        return pokemon;
    }
}
