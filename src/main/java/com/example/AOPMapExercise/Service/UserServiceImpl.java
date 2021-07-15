package com.example.AOPMapExercise.Service;

import com.example.AOPMapExercise.Mapper.MapStructMapper;
import com.example.AOPMapExercise.Repository.UserRepository;
import com.example.AOPMapExercise.api.model.CreateUserRequest;
import com.example.AOPMapExercise.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public UserRepository userRepository;

    @Override
    public User createNewUser(CreateUserRequest request) {
        User user = MapStructMapper.INSTANCE.userInformation(request);
        User createUser = userRepository.save(user);
        return createUser;
    }
}
