package Model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
@Entity(tableName = Book.tableName)
public class Book extends Booklet  {

    public static final String tableName = "book_table";

    @SerializedName("description")
    private String description = null;

    @SerializedName("photoUrl")
    private String photoUrl = null;

    @SerializedName("orderInfo")
    private OrderInfoEnum orderInfo = null;

    public enum OrderInfoEnum {
        @SerializedName("none")
        NONE("none"),

        @SerializedName("ebook")
        EBOOK("ebook"),

        @SerializedName("store")
        STORE("store");

        private String value;

        OrderInfoEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

        public static OrderInfoEnum fromString(String text) {
            for (OrderInfoEnum b : OrderInfoEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }

            throw new IllegalArgumentException("No constant with text " + text + " found");
        }
    }

    public Book(String title, String subtitle, Date releaseDate, String author, String description, String photoUrl, OrderInfoEnum orderInfo) {
        super(title, subtitle, releaseDate, author);
        this.description = description;
        this.photoUrl = photoUrl;
        this.orderInfo = orderInfo;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public OrderInfoEnum getOrderInfo() {
        return orderInfo;
    }
    public void setOrderInfo(OrderInfoEnum orderInfo) {
        this.orderInfo = orderInfo;
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
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(subtitle, book.subtitle) &&
                Objects.equals(releaseDate, book.releaseDate) &&
                Objects.equals(author, book.author) &&
                Objects.equals(description, book.description) &&
                Objects.equals(photoUrl, book.photoUrl) &&
                Objects.equals(orderInfo, book.orderInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, title, subtitle, releaseDate, author, description, photoUrl, orderInfo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Book {\n");

        sb.append("    Id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    subtitle: ").append(toIndentedString(subtitle)).append("\n");
        sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    photoUrl: ").append(toIndentedString(photoUrl)).append("\n");
        sb.append("    orderInfo: ").append(toIndentedString(orderInfo)).append("\n");
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
