package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleResource {

    private ResourceData data;

    public ResourceData getData() {
        return data;
    }
}
