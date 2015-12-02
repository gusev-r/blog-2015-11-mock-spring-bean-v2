package net.lkrnac.blog.testing.mockbeanv2.beans;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.lkrnac.blog.testing.mockbeanv2.SimpleApplication;

@ActiveProfiles("UserService-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SimpleApplication.class)
public class UserServiceITest {
	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Before
	public void resetSpy(){
		Mockito.reset(addressService);
	}

	@Test
	public void testGetUserDetails() {
		// GIVEN - spring context defined by Application class

		// WHEN
		String actualUserDetails = userService.getUserDetails("john");
 
		// THEN
		Assert.assertEquals("User john, 3 Dark Corner", actualUserDetails);
		verify(addressService, times(1)).getAddressForUser("john");
	}
} 
 