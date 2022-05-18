package guru.qa.parsing.tests.domain;

import com.google.gson.annotations.SerializedName;

public class QueryParams {
    @SerializedName("sort_by")
    public String sortBy;
    @SerializedName("category_id")
    public String categoryId;
}
