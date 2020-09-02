package org.mx.processlistservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Proc {
    private long id;
    private String content;

    public Proc() {}
    public Proc(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
