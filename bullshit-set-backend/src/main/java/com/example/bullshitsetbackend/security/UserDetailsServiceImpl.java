package com.example.bullshitsetbackend.security;

import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by pdybka on 03.06.16.
 */

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PlayerRepo playerRepository;
    private final static Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class.getName());



    @Autowired
    public UserDetailsServiceImpl(PlayerRepo playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.setLevel(Level.INFO);
        checkNotNull(username);

        if(username.isEmpty()) {
            throw new UsernameNotFoundException("Username cannot be empty");
        }

        Player player = playerRepository.findPlayerByUserName(username);
        if (player == null) {
            LOGGER.info("Username not found!");
            throw new UsernameNotFoundException("Player " + username + " doesn't exists");
        }
        LOGGER.info("Returning Player " + player.getUserName() + "  with password " + player.getPassword());
        return new ContextUser(player);
    }
}
