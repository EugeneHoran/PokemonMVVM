package working.com.exercise.eugene.pokemonhelper.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class PokedexData {
    @Id
    private Long id;
    private String pokemonNumber;
    private String name;
    private String url;

    @Generated(hash = 1533970782)
    public PokedexData(Long id, String pokemonNumber, String name, String url) {
        this.id = id;
        this.pokemonNumber = pokemonNumber;
        this.name = name;
        this.url = url;
    }

    @Generated(hash = 2061785570)
    public PokedexData() {
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPokemonNumber() {
        return this.pokemonNumber;
    }

    public void setPokemonNumber(String pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
