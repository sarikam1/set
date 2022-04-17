package com.example.bullshitsetbackend;

import com.example.bullshitsetbackend.DTO.UserDTO;
import com.example.bullshitsetbackend.controller.UserProfileController;
import com.example.bullshitsetbackend.domain.Game;
import com.example.bullshitsetbackend.domain.Participant;
import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.enums.GameStatus;
import com.example.bullshitsetbackend.repository.PlayerRepo;
import com.example.bullshitsetbackend.security.ContextUser;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.example.bullshitsetbackend.service.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Logger;


@SpringBootApplication(scanBasePackages = "com.example")
@RestController
public class BullshitSetBackendApplication {
    private final static Logger LOGGER = Logger.getLogger(BullshitSetBackendApplication.class.getName());


    public static void main(String[] args) {
        SpringApplication.run(BullshitSetBackendApplication.class, args);
    }

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public CommandLineRunner demo(PlayerRepo playerRepository) {
        return (args) -> {

            //save a couple of players
            playerRepository.save(new Player("ala", "ala@ala.com", new BCryptPasswordEncoder().encode("ala")));
            playerRepository.save(new Player("mary", "mary@mary.com",  new BCryptPasswordEncoder().encode("mary")));
            playerRepository.save(new Player("ok_name", "ok_email",  "ok_password"));


            Game game1 = new Game(playerService.findPlayerById(Long.valueOf(1)), Timestamp.from(Instant.now()), 1,
                    GameStatus.WAITING);
            gameService.addGame(game1);


        };
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @PostMapping("saveUser")
//    public void saveGameEntry(@RequestBody UserDTO userDto){
//        LOGGER.info("Saving user: " + userDto.getUsername());
//
//        Player player = new Player(userDto.getUsername(), passwordEncoder().encode(userDto.getPassword()));
//        ContextUser user = new ContextUser(player);
//        if(userDaoService.selectByName(userDto.getUsername()).isEmpty()){
//            userDaoService.save(user);
//        }
//    }




}
