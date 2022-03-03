package com.example.bullshitsetbackend.repository;

import com.example.bullshitsetbackend.domain.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SetRepo extends JpaRepository<Set, Long> {


}
