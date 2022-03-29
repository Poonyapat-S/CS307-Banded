package com.javainuse.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class newPostRequest {
    public String topicName;
    public String postTitle;
    public String postText;
    public Boolean isAnon;
}
