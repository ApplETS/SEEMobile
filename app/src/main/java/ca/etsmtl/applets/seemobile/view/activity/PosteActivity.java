package ca.etsmtl.applets.seemobile.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.presenter.PostePresenter;
import ca.etsmtl.applets.seemobile.utils.Constants;
import ca.etsmtl.applets.seemobile.view.PosteView;
import ca.etsmtl.applets.seemobile.view.adapter.PostePagerAdapter;

/**
 * Created by gnut3ll4 on 31/12/15.
 */
public class PosteActivity extends AppCompatActivity implements PosteView {

    private PostePagerAdapter postePagerAdapter;

    @Bind(R.id.container)
    ViewPager viewPager;

    @Bind(R.id.progressbar_details_poste)
    ProgressBar progressBar;

    private PostePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poste);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent myIntent = getIntent();
        String guidPoste = myIntent.getStringExtra(Constants.GUID_POSTE);

        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setOffscreenPageLimit(3);
        postePagerAdapter = new PostePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(postePagerAdapter);

        presenter = new PostePresenter(this, guidPoste);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPoste(Poste poste) {
        postePagerAdapter.setPoste(poste);
    }

}
