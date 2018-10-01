
package geek.example.rainicorn.data.models.owner.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Realname {

    @SerializedName("_content")
    @Expose
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
