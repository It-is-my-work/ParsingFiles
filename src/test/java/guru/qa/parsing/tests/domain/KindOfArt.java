package guru.qa.parsing.tests.domain;

import com.google.gson.annotations.SerializedName;

public class KindOfArt {
    public Integer id;
    @SerializedName("section_id")
    public Integer sectionId;
    public String name;
    public String slug;
    @SerializedName("h1_title")
    public String h1Title;
    @SerializedName("seo_title")
    public String seoTitle;
    @SerializedName("seo_description")
    public String seoDescription;
    public Boolean active;
    @SerializedName("query_params")
    public QueryParams queryParams;
    @SerializedName("parent_seo_page_id")
    public Integer parentSeoPageId;
    @SerializedName("children_count")
    public Integer childrenCount;
    public String description;
}

