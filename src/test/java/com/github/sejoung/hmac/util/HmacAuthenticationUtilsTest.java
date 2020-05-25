package com.github.sejoung.hmac.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HmacAuthenticationUtilsTest {

	@Test
	public void generateHMAC() {
		String msg = "{\"loyPgmNo\":\"LP0001\",\"trackingNo\":\"7f8d1e25-e766-11e6-91bc-a0b3ccc9c371\",\"tgNo\":\"A100\",\"siteCode\":\"30\",\"csrId\":\"test001\",\"pbpId\":\"1234567890\",\"actionType\":\"MW\"}";
		String keyString = "1234567890";
		String hmac = HmacAuthenticationUtils.generateHMAC(msg, keyString);

		Assertions.assertThat(hmac.toUpperCase()).as("틀려요")
				.isEqualTo("FC6304929A09E8BBEA24EF2F31762F2BA5445BB18F7427C0536147516C35D1D5");

	}
}