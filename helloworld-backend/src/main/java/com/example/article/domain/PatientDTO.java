package com.example.article.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String lastname;
	private String email;
	private String phone;
	private String active;
	private String hospitalname;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
