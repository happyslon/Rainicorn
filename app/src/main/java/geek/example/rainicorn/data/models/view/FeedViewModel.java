package geek.example.rainicorn.data.models.view;

import javax.annotation.Nullable;

import geek.example.rainicorn.data.models.RealmModel;

public class FeedViewModel {

    public String id;
    public String owner;
    public String title;
    public String urlS;

    public FeedViewModel(String id, String owner, String title, String urlS) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.urlS = urlS;
    }

    public FeedViewModel(RealmModel model) {
        this(model.id, model.owner, model.title, model.urlS);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
