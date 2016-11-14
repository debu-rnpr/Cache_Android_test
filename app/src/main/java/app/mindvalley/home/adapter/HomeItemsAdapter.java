package app.mindvalley.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mylibrary.interfces.DownloadCallback;
import com.mylibrary.request.Request;

import java.util.ArrayList;

import app.mindvalley.BR;
import app.mindvalley.R;
import app.mindvalley.home.Interfces.IitemListCallback;
import app.mindvalley.home.models.ApiResponse;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by debu on 11/11/16.
 */
public class HomeItemsAdapter extends RecyclerView.Adapter<HomeItemsAdapter.ItemViewHolder> {
    private ArrayList<ApiResponse> responseArraylist;
    private IitemListCallback callback;
    public HomeItemsAdapter(ArrayList<ApiResponse> responseArraylist,IitemListCallback callback){
        this.callback = callback;
        this.responseArraylist = responseArraylist;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        ItemViewHolder holder = new ItemViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.response,responseArraylist.get(holder.getAdapterPosition()));
        holder.getBinding().executePendingBindings();
        holder.init(holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount",""+responseArraylist.size());
        return responseArraylist.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;
        private ImageView iv_image;
        private CircleImageView profile;

        public ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            iv_image = (ImageView)itemView.findViewById(R.id.iv_image);
            profile = (CircleImageView) itemView.findViewById(R.id.profile_image);
        }

        public void init(final int pos){

            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onListItemClicked(iv_image , pos);
                }
            });

            Request requestBanner = new Request(responseArraylist.get(pos).getUrls().getRegular(),Request.TYPE_IMAGE);
            requestBanner.setDownloadCallback(new DownloadCallback<Bitmap>() {
                @Override
                public void onDownloadFinished(Bitmap bit) {
                    iv_image.setImageBitmap(bit);
                }

                @Override
                public void onDownloadError(String errMsg) {

                }
            });
            requestBanner.build();

            Request requestProfile = new Request(responseArraylist.get(pos).getUser().getProfile_image().getSmall(),Request.TYPE_IMAGE);
            requestProfile.setDownloadCallback(new DownloadCallback<Bitmap>() {
                @Override
                public void onDownloadFinished(Bitmap bit) {
                    profile.setImageBitmap(bit);
                }

                @Override
                public void onDownloadError(String errMsg) {
                    Log.e("erreor",errMsg);
                }
            });
            requestProfile.build();

        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
