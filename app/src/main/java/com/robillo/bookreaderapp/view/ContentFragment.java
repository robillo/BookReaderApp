package com.robillo.bookreaderapp.view;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robillo.bookreaderapp.R;

/**
 * Created by robinkamboj on 15/11/17.
 */

public class ContentFragment extends Fragment implements ContentMvpView {

    TextView mContentTextView;
    String content;

    public static ContentFragment newInstance(String content) {
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_content, container, false);

        setUp(v);

        return v;
    }

    @Override
    public void setUp(View v) {
        mContentTextView = v.findViewById(R.id.content_text);
        content = getArguments().getString("content");
        mContentTextView.setText(Html.fromHtml(content));
    }
}
