package app.mindvalley.home.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylibrary.request.Request;

import java.util.ArrayList;

import app.mindvalley.R;
import app.mindvalley.details.fragment.DetailsFragment;
import app.mindvalley.home.Interfces.IitemListCallback;
import app.mindvalley.home.MainActivity;
import app.mindvalley.home.adapter.HomeItemsAdapter;
import app.mindvalley.home.models.ApiResponse;
import app.mindvalley.home.presenter.IPresenterHome;
import app.mindvalley.home.presenter.PresenterHome;

/**
 * Created by debu on 11/11/16.
 */
public class HomeFragment extends Fragment implements IitemListCallback, IPresenterHome.IApiCallbackPresenter{
    private ArrayList<ApiResponse> allItemsResponse = new ArrayList<>();
    private HomeItemsAdapter homeItemsAdapter;
    private RecyclerView items;
    private SwipeRefreshLayout swipeRefreshLayout;
    private IPresenterHome.IApiRequest apiRequest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeItemsAdapter = new HomeItemsAdapter(allItemsResponse,this);
        apiRequest = new PresenterHome(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiRequest.getHomeItems("http://pastebin.com/raw/wgkJgazE/",Request.TYPE_JSON);
            }
        });
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        items = (RecyclerView)view.findViewById(R.id.itemsRecyclerView);
        items.setHasFixedSize(false);
        items.setLayoutManager(layoutManager);
        items.setNestedScrollingEnabled(false);
        items.setAdapter(homeItemsAdapter);
        apiRequest.getHomeItems("http://pastebin.com/raw/wgkJgazE/",Request.TYPE_JSON);
    }

    /*private void getItems(){
        Request request = new Request("http://pastebin.com/raw/wgkJgazE/",Request.TYPE_JSON);
        request.setTypeToken(new TypeToken<List<ApiResponse>>(){});
        request.setDownloadCallback(new DownloadCallback<ArrayList<ApiResponse>>() {
            @Override
            public void onDownloadFinished(ArrayList<ApiResponse> response) {
                if(response != null){
                    if(swipeRefreshLayout.isRefreshing())
                        swipeRefreshLayout.setRefreshing(false);
                    allItemsResponse.clear();
                    allItemsResponse.addAll(response);
                    homeItemsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onDownloadError(String errMsg) {

            }
        });
        request.build();
    }*/

    @Override
    public void onListItemClicked(View sharedElement, int pos) {
        DetailsFragment detailsFragment = new DetailsFragment();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailsFragment.setSharedElementEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.banner_transition));
            detailsFragment.setEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.slide_left));

            setSharedElementReturnTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.banner_transition));
            setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.slide_right));
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",allItemsResponse.get(pos));
        detailsFragment.setArguments(bundle);
        ((MainActivity)getContext()).addFragmentWithTransition(detailsFragment,sharedElement,getString(R.string.transition_banner));
    }

    @Override
    public void onResponseReceived(ArrayList<ApiResponse> responseArrayList) {
        if(responseArrayList != null){
            if(swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
            allItemsResponse.clear();
            allItemsResponse.addAll(responseArrayList);
            homeItemsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onApiError(String msg) {

    }
}
