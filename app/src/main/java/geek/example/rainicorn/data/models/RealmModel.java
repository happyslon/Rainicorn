package geek.example.rainicorn.data.models;

import java.io.Serializable;

import io.realm.RealmObject;


public class RealmModel extends RealmObject{

    private String owner;
    private String title;
    private String urlS;

    public RealmModel(){}


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlS() {
        return urlS;
    }

    public void setUrlS(String urlS) {
        this.urlS = urlS;
    }
}
