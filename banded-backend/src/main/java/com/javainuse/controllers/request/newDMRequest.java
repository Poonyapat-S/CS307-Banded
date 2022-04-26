package com.javainuse.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class newDMRequest {
	private String otherUserName;
	private String messageText;
}
