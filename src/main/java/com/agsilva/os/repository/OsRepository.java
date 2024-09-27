package com.agsilva.os.repository;

import com.agsilva.os.dominio.Os;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsRepository extends JpaRepository<Os, Integer> {
}
