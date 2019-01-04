package com.ssd.ssdapp.controller;

import com.ssd.ssdapp.repositories.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ssd.ssdapp.repositories.GameRepository;
import com.ssd.ssdapp.repositories.TeamRepository;
import com.ssd.ssdapp.model.Game;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class GameController {

    private GameRepository gameRepository;
    private TeamRepository teamRepository;

    @GetMapping("/games")
    public Iterable<Game> getAllGames()
    {
        return gameRepository.findAll();
    }

    @PostMapping("/addGame")
    public boolean saveGame(@RequestBody GameDTO gameDTO)
    {

        if(teamRepository.findByTeamName(gameDTO.getHomeTeam()) == null)
        {
            return false;
        }
        if(teamRepository.findByTeamName(gameDTO.getGuestTeam()) == null)
        {
            return false;
        }

        Game game = Game.builder()
                .homeTeam(gameDTO.getHomeTeam())
                .guestTeam(gameDTO.getGuestTeam())
                .build();

        gameRepository.save(game);
        return true;
    }

    @Autowired
    public void setGameRepository(GameRepository gameRepository, TeamRepository teamRepository)
    {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }
}

@Data
@NoArgsConstructor
class GameDTO
{
    private String homeTeam;
    private String guestTeam;
}
