package app.mindvalley.details.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.mylibrary.interfces.DownloadCallback;
import com.mylibrary.request.Request;

import app.mindvalley.R;
import app.mindvalley.details.presenter.IPresenterDetails;
import app.mindvalley.details.presenter.PresenterDetails;
import app.mindvalley.home.models.ApiResponse;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by debu on 13/11/16.
 */
public class DetailsFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener,IPresenterDetails.IImageCallbackPresenter{
    private ApiResponse response;
    private ImageView banner;
    private CircleImageView profile;
    private AppBarLayout app_bar;
    private IPresenterDetails.IImageRequest imageRequest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            response = (ApiResponse) getArguments().getSerializable("data");
        imageRequest = new PresenterDetails(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = (ImageView)view.findViewById(R.id.iv_image);
        profile = (CircleImageView)view.findViewById(R.id.profile_image);
        app_bar = (AppBarLayout) view.findViewById(R.id.app_bar);
        app_bar.addOnOffsetChangedListener(this);
        WebView webView = (WebView) view.findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(response.getUser().getLinks().getHtml());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        imageRequest.getImage(response.getUrls().getRegular());
        imageRequest.getImage(response.getUser().getProfile_image().getMedium());
    }

    //////can be optimized using RxJava
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if((appBarLayout.getHeight() + verticalOffset) < profile.getHeight()/2){
            profile.setVisibility(View.GONE);
        }else{
            profile.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onImageReceived(String url, Bitmap bitmap) {
        if(url.equals(response.getUrls().getRegular())){
            banner.setImageBitmap(bitmap);
        }else  if(url.equals(response.getUser().getProfile_image().getMedium())){
            profile.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onImageError(String msg) {

    }
}
