package com.example.bullshitsetbackend.repository;

import com.example.bullshitsetbackend.domain.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepo extends JpaRepository<Move, Long> {


}
