package com.example.dewidar.repository.square;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Squarevaluse implements Serializable {

    String name;
    String description;
    @SerializedName("owner")
    Ownervaluse ownervaluses = null;
    String fork;
    String html_url;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Ownervaluse getOwnervaluses() {
        return ownervaluses;
    }

    public String getFork() {
        return fork;
    }

    public String getHtml_url() {
        return html_url;
    }

    public Squarevaluse(String name, String description, Ownervaluse ownervaluses, String fork, String html_url) {
        this.name = name;
        this.description = description;
        this.ownervaluses = ownervaluses;
        this.fork = fork;
        this.html_url = html_url;


    }

    public static class Ownervaluse implements Serializable {
        String login;
        String html_url;

        public Ownervaluse(String login, String html_url) {
            this.login = login;
            this.html_url = html_url;
        }

        public String getLogin() {
            return login;
        }

        public String getHtml_url() {
            return html_url;
        }
    }

}




