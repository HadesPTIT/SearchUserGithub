package com.framgia.searchusergithub.screen.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framgia.searchusergithub.R;
import com.framgia.searchusergithub.constant.Constant;
import com.framgia.searchusergithub.data.model.User;

public class UserDetailActivity extends AppCompatActivity implements UserDetailContract.View {

    private UserDetailContract.Presenter mPresenter;
    private TextView mTvUserName;
    private TextView mTvGithubUrl;
    private ImageView mImvAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        setupView();

        mPresenter = new UserDetailPresenter(this);
        User user = getIntent().getExtras().getParcelable(Constant.EXTRA_USER_DETAIL);
        mPresenter.getUserInfo(user);

    }

    @Override
    public void setPresenter(UserDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void update(User user) {
        mTvUserName.setText(user.getLogin());
        mTvGithubUrl.setText(user.getHtmlUrl());
        Glide.with(this).load(user.getAvatarUrl()).into(mImvAvatar);
    }

    private void setupView() {
        mTvUserName = findViewById(R.id.text_user_name);
        mTvGithubUrl = findViewById(R.id.text_url);
        mImvAvatar = findViewById(R.id.image_avatar);
    }

}
