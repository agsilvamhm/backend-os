package com.agsilva.os.repository;

import com.agsilva.os.dominio.Cliente;
import com.agsilva.os.dominio.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT obj FROM Cliente obj WHERE obj.cpf =:cpf")
    Cliente findByCPF(@Param("cpf") String cpf);
}
