package working.com.exercise.eugene.pokemonmvvm.pokemonlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import working.com.exercise.eugene.pokemonhelper.db.PokedexData;
import working.com.exercise.eugene.pokemonmvvm.databinding.RecyclerPokemonItemBinding;

public class PokemonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PokemonItemNavigator mPokemonItemNavigator;
    private List<PokedexData> mPokemonList;

    public PokemonListAdapter(PokemonItemNavigator pokemonItemNavigator) {
        mPokemonItemNavigator = pokemonItemNavigator;
        mPokemonList = new ArrayList<>();
    }

    public void setPokemonItems(List<PokedexData> payoutList) {
        mPokemonList.clear();
        mPokemonList.addAll(payoutList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PokemonViewHolder(RecyclerPokemonItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder v, int position) {
        PokemonViewHolder holder = (PokemonViewHolder) v;
        PokemonListItemViewModel pokemonListItemViewModel = new PokemonListItemViewModel();
        RecyclerPokemonItemBinding recyclerPokemonItemBinding = holder.getDataBinding();
        recyclerPokemonItemBinding.setViewmodel(pokemonListItemViewModel);
        pokemonListItemViewModel.setPokemon(mPokemonList.get(position));
        pokemonListItemViewModel.setNavigator(mPokemonItemNavigator);
    }

    private class PokemonViewHolder extends RecyclerView.ViewHolder {
        private RecyclerPokemonItemBinding mPokemonBinding;

        public PokemonViewHolder(RecyclerPokemonItemBinding binding) {
            super(binding.getRoot());
            mPokemonBinding = binding;
        }

        public RecyclerPokemonItemBinding getDataBinding() {
            return mPokemonBinding;
        }
    }
}