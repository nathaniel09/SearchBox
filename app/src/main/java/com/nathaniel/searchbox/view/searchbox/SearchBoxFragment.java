package com.nathaniel.searchbox.view.searchbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nathaniel.searchbox.R;
import com.nathaniel.searchbox.model.data.Product;
import com.nathaniel.searchbox.presenter.ProductPresenter;
import com.nathaniel.searchbox.view.MainActivity;
import com.nathaniel.searchbox.view.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by USER on 9/13/2016.
 */
public class SearchBoxFragment extends BaseFragment implements SearchView.OnQueryTextListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ProductPresenter mProductPresenter;
    private SearchBoxAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String mQuery;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductPresenter = new ProductPresenter(getActivity());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SearchBoxAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_searchbox, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(false);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.d("onClick");
            }
        });

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Timber.d("onMenuItemActionCollapse");
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Timber.d("onMenuItemActionExpand");
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!TextUtils.isEmpty(query) && !query.equalsIgnoreCase(mQuery)) {
            mQuery = query;
            searchProduct(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Timber.d("onQueryTextChange");
        return false;
    }

    private void searchProduct(String query) {
        mProductPresenter.searchProduct(query, 0, new ProductPresenter.Callback<List<Product>>() {
            @Override
            public void onSuccess(List<Product> productList) {
                mAdapter.setProductList(productList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
