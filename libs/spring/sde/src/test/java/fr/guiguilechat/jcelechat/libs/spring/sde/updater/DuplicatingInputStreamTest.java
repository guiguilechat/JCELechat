package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DuplicatingInputStreamTest {

	@Test
	public void simpleTest() throws IOException {
		String testString = "Hello world";
		DuplicatingInputStream test = new DuplicatingInputStream(new ByteArrayInputStream(testString.getBytes()));
		Assert.assertEquals(new String(test.get().readAllBytes()), testString);
		Assert.assertEquals(new String(test.get().readAllBytes()), testString);
		Assert.assertEquals(new String(test.get().readAllBytes()), testString);

	}

}
