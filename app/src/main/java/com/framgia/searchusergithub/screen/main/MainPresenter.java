package com.framgia.searchusergithub.screen.main;

import android.text.TextUtils;

import com.framgia.searchusergithub.constant.Constant;
import com.framgia.searchusergithub.data.UserDataRepository;
import com.framgia.searchusergithub.data.model.GitResponse;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private UserDataRepository mUserDataRepository;

    public MainPresenter(MainContract.View view, UserDataRepository userDataRepository) {
        this.mView = view;
        this.mUserDataRepository = userDataRepository;
    }

    @Override
    public void search(String keyword, String limit) {

        if (TextUtils.isEmpty(keyword)) {
            mView.setKeyWordError(Constant.ERROR_FIELD_REQUIRED);
        } else if (TextUtils.isEmpty(limit)) {
            mView.setLimitError(Constant.ERROR_FIELD_REQUIRED);
        } else if (Integer.parseInt(limit) > 100 || Integer.parseInt(limit) < 0) {
            mView.setLimitError(Constant.ERROR_LIMIT);
        } else {
            mView.showLoginProgress(true);

            mUserDataRepository.getUsers(keyword, Integer.parseInt(limit)).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<GitResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(GitResponse gitResponse) {
                            mView.gotoUsersActivity(gitResponse.getUsers());
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showNetworkError();
                        }
                    });
        }
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void openTaskManager() {
        mView.gotoTaskActivity();
    }

}
