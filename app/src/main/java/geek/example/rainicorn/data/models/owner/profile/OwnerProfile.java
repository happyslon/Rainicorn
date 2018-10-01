
package geek.example.rainicorn.data.models.owner.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerProfile {

    @SerializedName("person")
    @Expose
    private Person person;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
