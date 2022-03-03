package com.example.bullshitsetbackend.repository;

import com.example.bullshitsetbackend.domain.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepo extends JpaRepository<Deck, Long> {


}
