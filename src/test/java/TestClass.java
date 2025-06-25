import org.barcode.core.user.domain.User;
import org.barcode.core.user.repository.UserMapper;
import org.barcode.core.user.service.UserService;

import org.barcode.web.user.dto.UserRequestRecord;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;

@ContextConfiguration(locations = {"classpath:/spring/root-context.xml", "classpath:/spring/servlet-context.xml"})
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestClass {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    private String userEmail="test133332";
    private String userPassword = "1234";
    private String userLastName = "jang";
    private String userFirstName = "shinwon";
    private String userPhoneNumber = "01012345678";
    private LocalDate userBirthDate = LocalDate.of(1980, 1, 1);


    @Test
    @Order(1)
    void createUser(){
        UserRequestRecord userRequestRecord = new UserRequestRecord(userEmail, userPassword, userLastName, userFirstName, userPhoneNumber, userBirthDate);
        User newUser = UserService.toUser(userRequestRecord);
        userService.signUp(userRequestRecord);
        User checkUser = userMapper.findUserByEmail(userEmail);
        System.out.println(checkUser);
    }



}
