package org.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.Entities.*;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
