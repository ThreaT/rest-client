package rest.client.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "resultCount",
    "results"
})
public class ItunesAlbumDto {

    @JsonProperty("resultCount")
    private Integer resultCount;
    @JsonProperty("results")
    private List<ItunesAlbumDtoResult> results = new ArrayList<>();
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("resultCount")
    public Integer getResultCount() {
        return resultCount;
    }

    @JsonProperty("resultCount")
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    @JsonProperty("results")
    public List<ItunesAlbumDtoResult> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<ItunesAlbumDtoResult> results) {
        this.results = results;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "ItunesAlbumDto{" + "resultCount=" + resultCount + ", results=" + results + ", additionalProperties=" + additionalProperties + '}';
    }

}
