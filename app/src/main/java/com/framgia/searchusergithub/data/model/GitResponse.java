package com.framgia.searchusergithub.data.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GitResponse {

    @SerializedName("total_count")
    @Expose
    private int mTotalCount;
    @SerializedName("incomplete_results")
    @Expose
    private boolean mIncompleteResults;
    @SerializedName("items")
    @Expose
    private List<User> mUsers;

    public List<User> getUsers() {
        return mUsers;
    }

    public int getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(int totalCount) {
        this.mTotalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return mIncompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.mIncompleteResults = incompleteResults;
    }

    public void setUsers(List<User> users) {
        this.mUsers = users;
    }
}