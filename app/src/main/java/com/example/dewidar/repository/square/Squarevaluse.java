package com.example.dewidar.repository.square;

import android.content.Context;

import java.io.Serializable;

  public class Squarevaluse implements Serializable {
      String repoName;
      String repoDescrption;
      String repoOwnerName;
      String repoFork;
      String repoUrl;
      String OwnerUrl;

      public Squarevaluse(String repoName, String repoDescrption,
                          String repoOwnerName, String repoFork, String repoUrl,
                          String ownerUrl) {
          this.repoName = repoName;
          this.repoDescrption = repoDescrption;
          this.repoOwnerName = repoOwnerName;
          this.repoFork = repoFork;
          this.repoUrl = repoUrl;
          OwnerUrl = ownerUrl;
      }

      public String getRepoName() {
          return repoName;
      }

      public void setRepoName(String repoName) {
          this.repoName = repoName;
      }

      public String getRepoDescrption() {
          return repoDescrption;
      }

      public void setRepoDescrption(String repoDescrption) {
          this.repoDescrption = repoDescrption;
      }

      public String getRepoOwnerName() {
          return repoOwnerName;
      }

      public void setRepoOwnerName(String repoOwnerName) {
          this.repoOwnerName = repoOwnerName;
      }

      public String getRepoFork() {
          return repoFork;
      }

      public void setRepoFork(String repoFork) {
          this.repoFork = repoFork;
      }

      public String getRepoUrl() {
          return repoUrl;
      }

      public void setRepoUrl(String repoUrl) {
          this.repoUrl = repoUrl;
      }

      public String getOwnerUrl() {
          return OwnerUrl;
      }

      public void setOwnerUrl(String ownerUrl) {
          OwnerUrl = ownerUrl;
      }
  }




