
package geek.example.rainicorn.data.models.owner.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nsid")
    @Expose
    private String nsid;
    @SerializedName("ispro")
    @Expose
    private Integer ispro;
    @SerializedName("can_buy_pro")
    @Expose
    private Integer canBuyPro;
    @SerializedName("iconserver")
    @Expose
    private Integer iconserver;
    @SerializedName("iconfarm")
    @Expose
    private Integer iconfarm;
    @SerializedName("path_alias")
    @Expose
    private String pathAlias;
    @SerializedName("has_stats")
    @Expose
    private Integer hasStats;
    @SerializedName("username")
    @Expose
    private Username username;
    @SerializedName("realname")
    @Expose
    private Realname realname;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("timezone")
    @Expose
    private Timezone timezone;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("photosurl")
    @Expose
    private Photosurl photosurl;
    @SerializedName("profileurl")
    @Expose
    private Profileurl profileurl;
    @SerializedName("mobileurl")
    @Expose
    private Mobileurl mobileurl;
    @SerializedName("photos")
    @Expose
    private Photos photos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public Integer getIspro() {
        return ispro;
    }

    public void setIspro(Integer ispro) {
        this.ispro = ispro;
    }

    public Integer getCanBuyPro() {
        return canBuyPro;
    }

    public void setCanBuyPro(Integer canBuyPro) {
        this.canBuyPro = canBuyPro;
    }

    public Integer getIconserver() {
        return iconserver;
    }

    public void setIconserver(Integer iconserver) {
        this.iconserver = iconserver;
    }

    public Integer getIconfarm() {
        return iconfarm;
    }

    public void setIconfarm(Integer iconfarm) {
        this.iconfarm = iconfarm;
    }

    public String getPathAlias() {
        return pathAlias;
    }

    public void setPathAlias(String pathAlias) {
        this.pathAlias = pathAlias;
    }

    public Integer getHasStats() {
        return hasStats;
    }

    public void setHasStats(Integer hasStats) {
        this.hasStats = hasStats;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Realname getRealname() {
        return realname;
    }

    public void setRealname(Realname realname) {
        this.realname = realname;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Photosurl getPhotosurl() {
        return photosurl;
    }

    public void setPhotosurl(Photosurl photosurl) {
        this.photosurl = photosurl;
    }

    public Profileurl getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(Profileurl profileurl) {
        this.profileurl = profileurl;
    }

    public Mobileurl getMobileurl() {
        return mobileurl;
    }

    public void setMobileurl(Mobileurl mobileurl) {
        this.mobileurl = mobileurl;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

}
