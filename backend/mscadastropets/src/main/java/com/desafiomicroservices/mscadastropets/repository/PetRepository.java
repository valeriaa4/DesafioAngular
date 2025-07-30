package com.desafiomicroservices.mscadastropets.repository;

import com.desafiomicroservices.mscadastropets.enums.Especie;
import com.desafiomicroservices.mscadastropets.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByRaca(String raca);
    List<Pet> findByEspecie(Especie especie);
}
