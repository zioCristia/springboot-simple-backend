package com.technicalTest.springbootApp.services;

import com.technicalTest.springbootApp.api.v1.mapper.UserMapper;
import com.technicalTest.springbootApp.api.v1.model.UserDTO;
import com.technicalTest.springbootApp.entities.UserInformation;
import com.technicalTest.springbootApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private UserRepository userRepository;

    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDto)
                .orElseThrow(RuntimeException::new);    // TODO: implement better exception handling
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserInformation userInformation = userMapper.userDtoToUser(userDTO);
        UserInformation savedUser = userRepository.save(userInformation);

        UserDTO returnDto = userMapper.userToUserDto(savedUser);

        return returnDto;
    }
}
