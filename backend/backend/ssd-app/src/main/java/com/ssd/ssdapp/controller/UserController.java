package com.ssd.ssdapp.controller;
import com.ssd.ssdapp.model.Team;
import com.ssd.ssdapp.repositories.TeamRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ssd.ssdapp.model.User;
import com.ssd.ssdapp.repositories.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {


    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @PostMapping("/saveUser")
    public boolean saveUser(@RequestBody UserDTO userDTO) {

        User user = User.builder()
                .name(userDTO.getName())
                .teamname(userDTO.getTeamname())
                .password(userDTO.getPassword())
                .build();

        Team team = Team.builder()
                .teamName(userDTO.getTeamname())
                .teamPoints(0)
                .build();

        if(user.getName().equals("") || user.getPassword().equals(""))
        {
            log.error("Empty username and password are not allowed");
            return false; // empty name or password
        }
        if(user.getName().equals("admin"))
        {
            log.error("You have no right to create admin account");
            return false;
        }

        userRepository.save(user);
        teamRepository.save(team);
        return true;
    }

    @PostMapping("/logUser")
    public int logUser(@RequestBody UserDTO userDTO) {
        if(userRepository.findByNameAndPassword(userDTO.getName(), userDTO.getPassword()) != null)
        {
            if(userDTO.getName().equals("admin") && userDTO.getPassword().equals("admin"))
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else if(userDTO.getName().equals("") || userDTO.getPassword().equals(""))
        {
            return -1;
        }
        else
        {
            return -1;
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

}
@Data
@NoArgsConstructor
class UserDTO {
    private String name;
    private String teamname;
    private String password;

}
