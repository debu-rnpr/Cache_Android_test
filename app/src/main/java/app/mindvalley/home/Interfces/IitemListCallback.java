package app.mindvalley.home.Interfces;

import android.view.View;

/**
 * used for communication between HomeItemsAdapter and Homefragment
 * Created by debu on 13/11/16.
 */
public interface IitemListCallback {
    /**
     *
     * @param sharedElement the view to be used for transition between fragments
     */
    void onListItemClicked(View sharedElement, int pos);
}
