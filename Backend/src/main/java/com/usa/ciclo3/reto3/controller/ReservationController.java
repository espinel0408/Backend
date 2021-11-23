package com.usa.ciclo3.reto3.controller;

import com.usa.ciclo3.reto3.model.Partyroom;
import com.usa.ciclo3.reto3.model.Reservation;
import com.usa.ciclo3.reto3.repository.CountClient;
import com.usa.ciclo3.reto3.service.PartyroomService;
import com.usa.ciclo3.reto3.service.ReservationService;
import com.usa.ciclo3.reto3.service.StatusReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ReservationController {


    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationService.getReservation(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return reservationService.deleteReservation(id);
    }

    @GetMapping("/report-status")
    public StatusReservation getReservation(){
        return reservationService.reporteStatusServicio();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservasTiempo (@PathVariable("dateOne")String dateOne, @PathVariable("dateTwo")String dateTwo ){
        return reservationService.reporteTiempoServicio(dateOne, dateTwo);
    }

    @GetMapping("/report-clients")
    public List<CountClient> getClientes(){
        return reservationService.reporteClientesServicio();
    }

}
