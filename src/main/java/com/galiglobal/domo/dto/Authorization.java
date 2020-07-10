
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
    "token",
    "permissions",
    "filters"
})
public class Authorization {

    @JsonProperty("token")
    private String token;
    @JsonProperty("permissions")
    private List<String> permissions = null;
    @JsonProperty("filters")
    private List<Filter> filters = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("permissions")
    public List<String> getPermissions() {
        return permissions;
    }

    @JsonProperty("permissions")
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @JsonProperty("filters")
    public List<Filter> getFilters() {
        return filters;
    }

    @JsonProperty("filters")
    public void setFilters(List<Filter> filters) {
        this.filters = filters;
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
