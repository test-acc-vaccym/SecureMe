package edu.berkeley.rescomp.secureme;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import edu.berkeley.rescomp.secureme.checklist.SecurityChecklist;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The content this fragment is presenting.
     */
    private SecurityChecklist.SecurityItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = SecurityChecklist.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the content as text in a TextView.
        if (mItem != null) {
            Activity context = getActivity();
            mItem.update(context);
            ((TextView) rootView.findViewById(R.id.item_detail))
                    .setText(context.getString(mItem.getDetailsId()));

            Button btnToNextScreen = (Button) rootView.findViewById(R.id.item_detail_button);
            if (mItem.getIntent() != null) {
                btnToNextScreen.setText(mItem.getButtonTextId());
                btnToNextScreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mItem.getIntent()));
                    }
                });
                if (btnToNextScreen.getVisibility() != View.VISIBLE) {
                    btnToNextScreen.setVisibility(View.VISIBLE);
                }
            } else if (btnToNextScreen != null) {
                    btnToNextScreen.setVisibility(View.GONE);
                }
            }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mItem != null) {
            Activity context = getActivity();
            mItem.update(context);
            ((TextView) context.findViewById(R.id.item_detail))
                    .setText(context.getString(mItem.getDetailsId()));
        }
    }
}
