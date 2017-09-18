package working.com.exercise.eugene.pokemonmvvm.pokemonlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import working.com.exercise.eugene.pokemonmvvm.databinding.FragmentPokemonListBinding;


public class PokemonListFragment extends Fragment implements PokemonItemNavigator {

    public PokemonListFragment() {
        // Requires empty public constructor
    }

    public static PokemonListFragment newInstance() {
        return new PokemonListFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupListAdapter();
    }

    private FragmentPokemonListBinding mPokemonListFragBinding;
    private PokemonListViewModel mPokemonViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPokemonListFragBinding = FragmentPokemonListBinding.inflate(inflater, container, false);
        mPokemonViewModel = new PokemonListViewModel(getActivity());
        mPokemonListFragBinding.setViewmodel(mPokemonViewModel);
        return mPokemonListFragBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPokemonViewModel.loadPokemon();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPokemonViewModel.clearSubscriptions();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupListAdapter() {
        RecyclerView recyclerView = mPokemonListFragBinding.recyclerPokedex;
        PokemonListAdapter pokemonListAdapter = new PokemonListAdapter(this);
        recyclerView.setAdapter(pokemonListAdapter);
    }

    @Override
    public void openPokemonDetails(long pokemonId) {
        Toast.makeText(getActivity(), pokemonId + " test", Toast.LENGTH_SHORT).show();
    }
}
