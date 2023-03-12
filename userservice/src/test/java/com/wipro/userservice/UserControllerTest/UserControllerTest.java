package com.wipro.userservice.UserControllerTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.userservice.controller.UserController;
import com.wipro.userservice.exception.UserNotFoundException;
import com.wipro.userservice.model.User;
import com.wipro.userservice.repository.UserRepository;
import com.wipro.userservice.services.JwtSecurityTokenGeneratorImpl;
import com.wipro.userservice.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserControllerTest {
	
		@InjectMocks
	    private UserController userController;

	    @Mock
	    private UserServiceImpl userService;
	    
	    @Mock
	    private UserRepository userRepo;
	    
	    @Mock
	    private JwtSecurityTokenGeneratorImpl tokenGenerator;
	    @Autowired
	    private MockMvc mockMvc;
	    
	    @Before
	    public void setup() {
	        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	    }
	    
	    @Test
	    public void testRegisterUser() throws UserNotFoundException {
	        // create a test user
	        User user = new User("sai123", "sai", "babu", "passward","saibabu@gmail.com", new Date());

	        // call the method being tested
	        ResponseEntity<?> responseEntity = userController.registerUser(user);

	        // assert that the response has the expected status code
	        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

	        // assert that the user was saved to the database
//	        User savedUser = userService.findByUserIdAndPassword("sai123","password");
//	        assertNotNull(savedUser);
//	        assertEquals("sai", savedUser.getFirstName());
//	        assertEquals("babu", savedUser.getLastName());
//	        assertEquals("saibabu@gmail.com", savedUser.getEmail());
//	        assertEquals("password", savedUser.getPassword());
	        
	    }
	    
	   

	    
	    
	    
	    

	    @Test
	    public void testLogin_Success() throws Exception {
	        User user = new User();
	        user.setUserId("testuser");
	        user.setPassword("testpassword");

	        Map<String, String> tokenMap = new HashMap<>();
	        tokenMap.put("token", "testtoken");

	        when(userService.findByUserIdAndPassword("testuser", "testpassword")).thenReturn(user);
	        when(tokenGenerator.generateToken(user)).thenReturn(tokenMap);

	        mockMvc.perform(post("/api/v1/userservice/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(user)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.token").value("testtoken"));
	    }

	    @Test
	    public void testLogin_Failure() throws Exception {
	        User user = new User();
	        user.setUserId("testuser");
	        user.setPassword("testpassword");

	        when(userService.findByUserIdAndPassword("testuser", "testpassword")).thenReturn(null);

	        mockMvc.perform(post("/api/v1/userservice/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(user)))
            		.andExpect(status().isOk());
	                
	    }

	    @Test
	    public void testLogin_EmptyUserId() throws Exception {
	        User user = new User();
	        user.setUserId(null);
	        user.setPassword("testpassword");

	        mockMvc.perform(post("/api/v1/userservice/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(user)))
	                .andExpect(status().isUnauthorized());
	    }

	    @Test
	    public void testLogin_EmptyPassword() throws Exception {
	        User user = new User();
	        user.setUserId("testuser");
	        user.setPassword(null);

	        mockMvc.perform(post("/api/v1/userservice/login")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(user)))
	                .andExpect(status().isUnauthorized());
	                
	    }
	    
	    
//	    @Test
//	    public void testGetAllUsers() throws Exception {
//	        List<User> userList = Arrays.asList(
//	            new User("1", "sai", "babu", "123456","saibabu@gmail.com", new Date()),
//	            new User("2", "sai", "ram", "123456","sairam@gmail.com", new Date()),
//	            new User("3", "sai", "krishna", "123456","saikrishan@gmail.com", new Date())
//	        );
//	        when(userRepo.findAll()).thenReturn(userList);
//	        ResponseEntity<?> response =  userController.getAllUsers();
//	        assertEquals(HttpStatus.OK, response.getStatusCode());
//	        assertEquals(userList, response.getBody());
//	        verify(userRepo, times(1)).findAll();
//	    }
//	    @Test
//	    public void testGetAllUsersWithNoUsers() throws Exception {
//	        when(userRepo.findAll()).thenReturn(Collections.emptyList());
//	        ResponseEntity<?> response = userController.getAllUsers();
//	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//	        verify(userRepo, times(1)).findAll();    
//	        
//	    
//}

	    
	    
	    
	    
	    
	    


}
