package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUser {

    private UserData data;

    public UserData getData() {
        return data;
    }
}
