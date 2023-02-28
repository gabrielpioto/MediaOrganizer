package com.mediazer.mediaguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediazer.mediaguide.views.PinnedParallaxScrollView;

/**
 * Created by gabriel on 05/06/15.
 */
public class EpisodePageFragment extends Fragment{

    private int position = -1;
    private PinnedParallaxScrollView.OnScrollListener barListener;
    private PinnedParallaxScrollView scrollView;

    public EpisodePageFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        scrollView = (PinnedParallaxScrollView) inflater.inflate(R.layout.episode_page, container, false);

        scrollView.setOnScrollListener(barListener);
        ((TextView) scrollView.findViewById(R.id.txt_title)).setText("Pagina " + (position + 1));
        int resImg = (position % 2 == 1) ? R.drawable.brba : R.drawable.brba2;
        ((ImageView) scrollView.findViewById(R.id.image_header)).setImageResource(resImg);

        return scrollView;
    }


    public static EpisodePageFragment newInstance(int position, PinnedParallaxScrollView.OnScrollListener barListener) {
        EpisodePageFragment f = new EpisodePageFragment();
        f.position = position;
        f.barListener = barListener;
        return f;
    }
}
