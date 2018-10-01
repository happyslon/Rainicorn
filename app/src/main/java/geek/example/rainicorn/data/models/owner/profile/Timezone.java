
package geek.example.rainicorn.data.models.owner.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timezone {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("offset")
    @Expose
    private String offset;
    @SerializedName("timezone_id")
    @Expose
    private String timezoneId;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

}
