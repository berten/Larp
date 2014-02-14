package org.deschutter.authentication.user;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.deschutter.user.UserDao;
import org.deschutter.user.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryImplTest {

	@InjectMocks
	private UserRepository userRepository = new UserRepositoryImpl();

	@Mock
	private UserDao userDao;

	@Test
	public void findByUserNameAndPassword() {
		UserEntity userEntity = new StubbedUserEntity("username", "password", 123L);
		when(userDao.findByUsernameAndPassword("username", "password")).thenReturn(userEntity);
		User result = userRepository.findByUsernameAndPassword("username", "password");
		assertSame(123L, result.getUserId());
	}

	private class StubbedUserEntity extends UserEntity {
		private final Long id;

		public StubbedUserEntity(String username, String password, Long id) {
			super(username, password);
			this.id = id;
		}

		public Long getId() {
			return id;
		}
	}
}
