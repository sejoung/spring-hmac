package com.github.sejoung.hmac.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class Test {

	private String data;

	@Builder
	private Test(String data) {
		this.data = data;
	}

}
