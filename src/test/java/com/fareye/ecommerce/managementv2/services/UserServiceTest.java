package com.fareye.ecommerce.managementv2.services;

import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.fareye.ecommerce.managementv2.models.User;
import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private static UserRepository userRepositoryMock;

    @InjectMocks
    UserService userService;

    @Test
    public void testCreateAdmin() {
        User user = new User(2L,
                "vibsharma",
                "password",
                "Vaibhav",
                "Sharma",
                "Admin",
                true,
                "vbvsharma@gmail.com");

        when(userRepositoryMock.save(user)).thenReturn(user);
        Assert.assertEquals(user, userService.createAdmin(user));


        user = new User(2L,
                "vibsharma",
                "password",
                "Vaibhav",
                "Sharma",
                "Seller",
                true,
                "vbvsharma@gmail.com");

        Assert.assertEquals(null, userService.createAdmin(user));
    }


    @Test
    public void testCreateSeller() {
        User user = new User(2L,
                "vibsharma",
                "password",
                "Vaibhav",
                "Sharma",
                "Seller",
                true,
                "vbvsharma@gmail.com");

        when(userRepositoryMock.save(user)).thenReturn(user);
        Assert.assertEquals(user, userService.createSeller(user));


        user = new User(2L,
                "vibsharma",
                "password",
                "Vaibhav",
                "Sharma",
                "Admin",
                true,
                "vbvsharma@gmail.com");

        Assert.assertEquals(null, userService.createSeller(user));
    }
}
