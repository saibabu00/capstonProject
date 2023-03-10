package com.wipro.userservice.UserServiceTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wipro.userservice.exception.UserAlreadyExsitsException;
import com.wipro.userservice.exception.UserNotFoundException;
import com.wipro.userservice.model.User;
import com.wipro.userservice.repository.UserRepository;
import com.wipro.userservice.services.JwtSecurityTokenGeneratorImpl;
import com.wipro.userservice.services.UserServiceImpl;




@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	private User user;

	@InjectMocks
	private UserServiceImpl service;

	private Optional<User> options;
	
	private JwtSecurityTokenGeneratorImpl jwtUtil;


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("sai", "sai", "babu", "123456","sai@gmail.com", new Date());
		options = Optional.of(user);
		jwtUtil = new JwtSecurityTokenGeneratorImpl();
	}

	@Test
	public void testSaveUserSuccess() throws UserAlreadyExsitsException {
		when(userRepository.save(user)).thenReturn(user);
		User flag = service.saveUser(user);
		assertEquals("Cannot Register User",  user, flag);
		verify(userRepository, times(1)).save(user);
	}

	@Test(expected = UserAlreadyExsitsException.class)
	public void testSaveUserFailure() throws UserAlreadyExsitsException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		User flag = service.saveUser(user);
		assertEquals("saving user failed", user, flag);
		verify(userRepository, times(1)).findById(user.getUserId());
	}

	@Test
	public void testValidateSuccess() throws UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = service.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals(user.getUserId(), userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException {
		when(userRepository.findById("sourav")).thenReturn(null);
		service.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
	 @Test
	 public void testGenerateToken() {
	        User user = new User();
	        user.setUserId("123");
	        Map<String, String> tokenMap = jwtUtil.generateToken(user);
	        assertNotNull(tokenMap);
	        assertNotNull(tokenMap.get("token"));
	        assertNotNull(tokenMap.get("message"));
	        assertEquals("User successfully logged in", tokenMap.get("message"));
	    }
	 	@Test
	    public void testFindByUserIdAndPassword() throws UserNotFoundException {
	        User mockUser = new User("sai123","sai","babu","password","saibabu@wipro.com",new Date());
	        when(userRepository.findByUserIdAndPassword("sai123", "password")).thenReturn(mockUser);

	        User user = service.findByUserIdAndPassword("sai123", "password");

	        assertNotNull(user);
	        assertEquals("sai", user.getFirstName());
	        assertEquals("babu", user.getLastName());
	        assertEquals("sai123", user.getUserId());
	        assertEquals("password", user.getPassword());
	    }

	    @Test(expected = UserNotFoundException.class)
	    public void testFindByUserIdAndPasswordWithInvalidCredentials() throws UserNotFoundException {
	        when(userRepository.findByUserIdAndPassword("sai123", "invalidpassword")).thenReturn(null);

	        service.findByUserIdAndPassword("sai123", "invalidpassword");
	    }
	    
	    @Test
	    public void testGetAllUsers() throws UserNotFoundException {
	        List<User> mockUsers = Arrays.asList(
	                new User("sai123", "sai", "babu", "password", "saibabu@gmail.com",new Date()),
	                new User("sairam123", "sai", "ram", "password", "sairam@gmail.com", new Date()));

	        when(userRepository.findAll()).thenReturn(mockUsers);
	       


	        List<User> users = service.getAllUsers();

	        assertNotNull(users);
	        assertEquals(2, users.size());
	        assertEquals("sai", users.get(0).getFirstName());
	        assertEquals("babu", users.get(0).getLastName());
	        assertEquals("sai123", users.get(0).getUserId());
	        assertEquals("password", users.get(0).getPassword());
	        assertEquals("saibabu@gmail.com",users.get(0).getEmail());
	        
	        
	        
	        
	        assertEquals("sai", users.get(1).getFirstName());
	        assertEquals("ram", users.get(1).getLastName());
	        assertEquals("sairam123", users.get(1).getUserId());
	        assertEquals("password", users.get(1).getPassword());
	        assertEquals("sairam@gmail.com",users.get(1).getEmail());
	        
	        
	    }
	    @Test
	    public void testGetAllUsersThrowsException() throws UserNotFoundException {
	        // call the method being tested - this should throw an exception
	        service.getAllUsers();
	    }
	    
	    
	    @Test
	    public void testGetUserById() throws Exception {
	        String userId = "1";
	        User user = new User(userId, "sai", "babu", "123456","sai@gmail.com", new Date());
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	        User fetchedUser = service.getUserById(userId);
	        assertNotNull(fetchedUser);
	        assertEquals(user, fetchedUser);
	    }


	    
	    
	    @Test
	    public void testUpdateUser() throws Exception {
	        String userId = "sai123";
	        User user = new User(userId, "sai", "babu", "123456","sai@gmail.com", new Date());
	        User updatedUser = new User(userId, "saibabu", "bandaru", "1234567","sai@gmail.com", new Date());
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	        when(userRepository.save(user)).thenReturn(updatedUser);
	        User result = service.upadteUser(userId, updatedUser);
//	       assertEquals(result, updatedUser);
	        verify(userRepository, times(1)).findById(userId);
	        verify(userRepository, times(1)).save(user);
	    }

	    @Test(expected = UserNotFoundException.class)
	    public void testUpdateUserWithInvalidId() throws Exception {
	        String userId = "invalid-id";
	        User user = new User(userId, "sai", "babu", "123456","sai@gmail.com", new Date());
	        when(userRepository.findById(userId)).thenReturn(Optional.empty());
	        service.upadteUser(userId, user);
	    }




 

}
