package com.example.dewidar.repository.square;

import android.content.Context;

public class Squarevaluse {

    String repoName;
    String repoDescrption;
    String repoOwnerName;
    String repoFork;
    String repoUrl;
    String OwnerUrl;
    Context context;


    public Squarevaluse(String repoName, String repoDescrption, String repoOwnerName,
                        String repoFork, String repoUrl, String ownerUrl, Context context) {
        this.repoName = repoName;
        this.repoDescrption = repoDescrption;
        this.repoOwnerName = repoOwnerName;
        this.repoFork = repoFork;
        this.repoUrl = repoUrl;
        this.OwnerUrl = ownerUrl;
        this.context = context;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public void setRepoDescrption(String repoDescrption) {
        this.repoDescrption = repoDescrption;
    }

    public void setRepoOwnerName(String repoOwnerName) {
        this.repoOwnerName = repoOwnerName;
    }

    public void setRepoFork(String repoFork) {
        this.repoFork = repoFork;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public void setOwnerUrl(String ownerUrl) {
        OwnerUrl = ownerUrl;
    }

    public String getRepoName() {

        return repoName;
    }

    public String getRepoDescrption() {
        return repoDescrption;
    }

    public String getRepoOwnerName() {
        return repoOwnerName;
    }

    public String getRepoFork() {
        return repoFork;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public String getOwnerUrl() {
        return OwnerUrl;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}




