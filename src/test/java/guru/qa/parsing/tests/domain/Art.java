package guru.qa.parsing.tests.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Art {
    @SerializedName("total_count")
    public Integer totalCount;
    public List<KindOfArt> data;
}
