package org.deschutter.authentication;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.deschutter.authentication.user.User;
import org.deschutter.authentication.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@RunWith(MockitoJUnitRunner.class)
public class DBAuthenticationProviderTest {

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	@InjectMocks
	private DBAuthenticationProvider authenticationProvider;

	@Mock
	private UserRepository userRepository;

	private User user;
	private TestingAuthenticationToken authentication;

	@Before
	public void setUp() throws Exception {
		user = mock(User.class);
		when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(user);
		authentication = new TestingAuthenticationToken(USERNAME, PASSWORD);
	}

	@Test
	public void authenticate_HasUser() throws Exception {
		Authentication authentication = authenticationProvider.authenticate(this.authentication);
		assertEquals(user, authentication.getPrincipal());
	}

	@Test
	public void authenticate_HasCredentials() throws Exception {
		Authentication authentication = authenticationProvider.authenticate(this.authentication);
		assertEquals(this.authentication, authentication.getCredentials());
	}

	@Test
	public void authenticate_HasAuthority_USER() throws Exception {
		Authentication authentication = authenticationProvider.authenticate(this.authentication);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		assertEquals(1, authorities.size());
		assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
	}

	@Test(expected = BadCredentialsException.class)
	public void authenticate_DoesNotFindUser_ThrowsException() {
		when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(null);
		authenticationProvider.authenticate(authentication);
	}

	@Test
	public void supports_DoesNotSupportTestingAuthentication() {
		assertFalse(authenticationProvider.supports(TestingAuthenticationToken.class));
	}

	@Test
	public void supports_SupportsusernamePasswordToken() {
		assertTrue(authenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
	}

}
