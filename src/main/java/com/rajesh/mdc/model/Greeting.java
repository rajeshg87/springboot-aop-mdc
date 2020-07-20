package com.rajesh.mdc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {

	private Integer id;
	private String message;
	private Integer points;
}
