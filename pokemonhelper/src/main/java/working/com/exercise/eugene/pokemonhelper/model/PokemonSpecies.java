package working.com.exercise.eugene.pokemonhelper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonSpecies {
    @SerializedName("name")
    @Expose
    private String pokemonName;

    @SerializedName("url")
    @Expose
    private String pokemonUrl;

    public String getPokemonName() {
        return pokemonName;
    }

    public String getPokemonUrl() {
        return pokemonUrl;
    }
}
