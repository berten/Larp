package org.deschutter.authentication.user;

import static junit.framework.TestCase.assertSame;

import org.junit.Test;

public class UserTest {

	@Test
	public void user_constructor() {
		User user = new User(123L);
		assertSame(123L, user.getUserId());
	}
}
