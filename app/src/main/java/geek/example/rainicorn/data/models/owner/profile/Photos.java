
package geek.example.rainicorn.data.models.owner.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("firstdatetaken")
    @Expose
    private Firstdatetaken firstdatetaken;
    @SerializedName("firstdate")
    @Expose
    private Firstdate firstdate;
    @SerializedName("count")
    @Expose
    private Count count;

    public Firstdatetaken getFirstdatetaken() {
        return firstdatetaken;
    }

    public void setFirstdatetaken(Firstdatetaken firstdatetaken) {
        this.firstdatetaken = firstdatetaken;
    }

    public Firstdate getFirstdate() {
        return firstdate;
    }

    public void setFirstdate(Firstdate firstdate) {
        this.firstdate = firstdate;
    }

    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }

}
