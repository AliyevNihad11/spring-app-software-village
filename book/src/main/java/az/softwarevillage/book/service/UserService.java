package az.softwarevillage.book.service;

import az.softwarevillage.book.dto.request.UserRequest;
import az.softwarevillage.book.dto.response.BasaResponse;
import az.softwarevillage.book.dto.response.UserResponse;
import az.softwarevillage.book.enums.EnumAvailableStatus;
import az.softwarevillage.book.exception.UserExistException;
import az.softwarevillage.book.exception.UserNotFoundException;
import az.softwarevillage.book.model.User;
import az.softwarevillage.book.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BasaResponse registerUser(UserRequest userRequest) {
        checkUsernameAndEmail(userRequest);

        // Yeni istifadəçi yaratmaq
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setPhone(userRequest.getPhone());
        user.setStatus(EnumAvailableStatus.ACTIVE.getValue());  // Varsayılan olaraq ACTIVE statusunu təyin et

        userRepository.save(user);  // Yeni istifadəçini qeydiyyatdan keçir
        return BasaResponse.getSuccessMessage();
    }

    private void checkUsernameAndEmail(UserRequest userRequest) {
        List<UserResponse> users = getAllUsers();  // `getAllUsers()` əvəzinə `userRepository.findAll()` istifadə et
        for (UserResponse u : users) {
            if (u.getEmail().equals(userRequest.getEmail())) {
                throw new UserExistException("Email already exist!");
            }

            if (u.getUsername().equals(userRequest.getUsername())) {
                throw new UserExistException("Username already exist!");
            }
        }
    }

    // `User` modelindən `UserResponse`-yə çevirmək
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAllByStatus(EnumAvailableStatus.ACTIVE.getValue());
        return getUserResponseList(users);  // `getUserResponseList` metodundan istifadə edirik
    }

    public UserResponse getUserById(Long id) {
        User user = checkUser(id, "User not Found!");

        return mapEntityToResponse(user);
    }

    private  UserResponse mapEntityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setPassword(user.getPassword());
        userResponse.setPhone(user.getPhone());
        userResponse.setUsername(user.getUsername());

        return userResponse;
    }

    public BasaResponse updateUser(Long id,UserRequest userRequest){

        User user = checkUser(id, "User not found!");

        user.setFirstname(userRequest.getFirstname());
        user.setUsername(userRequest.getUsername());
        user.setLastname(userRequest.getLastname());
        user.setPassword(userRequest.getPassword());
        user.setPhone(userRequest.getPhone());
        user.setEmail(userRequest.getEmail());
        userRepository.save(user);

        return BasaResponse.getSuccessMessage();
    }

    private User checkUser(Long id, String message) {
        User user = userRepository.findByIdAndStatus(id, EnumAvailableStatus.ACTIVE.getValue());

        if (user == null) {
            throw new UserNotFoundException(message);
        }
        return user;
    }

    private User mapResponseToEntity(UserResponse userResponse) {

            User user = new User(); // Yeni User obyekti yarat
            user.setId(userResponse.getId());
            user.setEmail(userResponse.getEmail());
            user.setFirstname(userResponse.getFirstname());
            user.setLastname(userResponse.getLastname());
            user.setPassword(userResponse.getPassword());
            user.setPhone(userResponse.getPhone());
            user.setUsername(userResponse.getUsername());

            return user;


    }


    // `User` listini `UserResponse` listinə çevirmək
    private List<UserResponse> getUserResponseList(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();

        for (User u : users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(u.getId());
            userResponse.setEmail(u.getEmail());
            userResponse.setFirstname(u.getFirstname());
            userResponse.setLastname(u.getLastname());
            userResponse.setPhone(u.getPhone());
            userResponse.setUsername(u.getUsername());
            userResponse.setPassword(u.getPassword());


            userResponses.add(userResponse);
        }

        return userResponses;
    }
}
