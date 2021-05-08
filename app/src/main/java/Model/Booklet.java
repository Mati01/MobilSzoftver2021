package Model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.PrimaryKey;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class Booklet   {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id")
    protected Integer id = null;

    @SerializedName("title")
    protected String title = null;

    @SerializedName("subtitle")
    protected String subtitle = null;

    @SerializedName("releaseDate")
    protected Date releaseDate = null;

    @SerializedName("author")
    protected String author = null;

    public Booklet(String title, String subtitle, Date releaseDate, String author) {
        this.title = title;
        this.subtitle = subtitle;
        this.releaseDate = releaseDate;
        this.author = author;
    }

    /**
     **/
    @ApiModelProperty(required = true, value = "")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     **/
    @ApiModelProperty(required = true, value = "")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    /**
     **/
    @ApiModelProperty(required = true, value = "")
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }


    /**
     **/
    @ApiModelProperty(required = true, value = "")
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Booklet booklet = (Booklet) o;
        return Objects.equals(id, booklet.id) &&
                Objects.equals(title, booklet.title) &&
                Objects.equals(subtitle, booklet.subtitle) &&
                Objects.equals(releaseDate, booklet.releaseDate) &&
                Objects.equals(author, booklet.author);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, title, subtitle, releaseDate, author);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Booklet {\n");

        sb.append("    Id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    subtitle: ").append(toIndentedString(subtitle)).append("\n");
        sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
