package telegraph.publication;

import com.google.gson.annotations.SerializedName;

public enum PublicationType {
    @SerializedName("article") ARTICLE,
    @SerializedName("video") VIDEO,
    ;
}
