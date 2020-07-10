
package com.galiglobal.domo.dto;

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
    "sessionLength",
    "authorizations"
})
public class DomoRequest {

    @JsonProperty("sessionLength")
    private Integer sessionLength;
    @JsonProperty("authorizations")
    private List<Authorization> authorizations = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sessionLength")
    public Integer getSessionLength() {
        return sessionLength;
    }

    @JsonProperty("sessionLength")
    public void setSessionLength(Integer sessionLength) {
        this.sessionLength = sessionLength;
    }

    @JsonProperty("authorizations")
    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    @JsonProperty("authorizations")
    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
