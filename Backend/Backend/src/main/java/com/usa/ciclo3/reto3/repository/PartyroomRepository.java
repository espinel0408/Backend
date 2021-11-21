package com.usa.ciclo3.reto3.repository;

import com.usa.ciclo3.reto3.crudrepository.PartyroomCrudRepository;
import com.usa.ciclo3.reto3.model.Partyroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PartyroomRepository {

    @Autowired
    private PartyroomCrudRepository partyroomCrudRepository;

    public List<Partyroom> getAll() {
        return (List<Partyroom>) partyroomCrudRepository.findAll();
    }
    public Optional<Partyroom> getPartyroom(int id){
        return partyroomCrudRepository.findById(id);
    }
    public Partyroom save(Partyroom partyroom){
        return partyroomCrudRepository.save(partyroom);
    }
    public void delete(Partyroom partyroom){
        partyroomCrudRepository.delete(partyroom);
    }

}
