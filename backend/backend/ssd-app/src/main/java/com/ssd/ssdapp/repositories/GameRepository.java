package com.ssd.ssdapp.repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import com.ssd.ssdapp.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findByHomeTeamAndGuestTeam(String hometeam, String guestteam);

}
