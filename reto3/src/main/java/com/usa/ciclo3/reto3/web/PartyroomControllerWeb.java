package com.usa.ciclo3.reto3.web;

import java.util.List;
import java.util.Optional;

import com.usa.ciclo3.reto3.model.Partyroom;
import com.usa.ciclo3.reto3.services.PartyroomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Partyroom")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class PartyroomControllerWeb {

    @Autowired
    private PartyroomServices partyroomServices;

    @GetMapping("/all")
    public List<Partyroom> getPartyroom(){

        return partyroomServices.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Partyroom> getPartyroom (@PathVariable("id") int id) {

        return partyroomServices.getPartyroom(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Partyroom save(@RequestBody Partyroom partyroom) {

        return partyroomServices.save(partyroom);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)

    public Partyroom update(@RequestBody Partyroom partyroom) {

        return partyroomServices.update(partyroom);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return partyroomServices.deletePartyroom(id);
    }


}
