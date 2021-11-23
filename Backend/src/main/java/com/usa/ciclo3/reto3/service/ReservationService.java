package com.usa.ciclo3.reto3.service;

import com.usa.ciclo3.reto3.model.Reservation;
import com.usa.ciclo3.reto3.repository.CountClient;
import com.usa.ciclo3.reto3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    /*Con estos metodo consultamos los datos de
    **la reservacion
    */
    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int idReservation) {
        return reservationRepository.getReservation(idReservation);
    }
    /*Con este metodo guardamos los datos de
    **la reservacion
    */
    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> tmpReservation = reservationRepository.getReservation(reservation.getIdReservation());
            if (tmpReservation.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }
    /*Con este metodo actualizamos los datos de
    **la reservacion
    */
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> e = reservationRepository.getReservation(reservation.getIdReservation());
            if (!e.isEmpty()) {

                if (reservation.getStartDate() != null) {
                    e.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    e.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(e.get());
                return e.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }
    /*Con este metodo borramos los datos de
    **la reservacion
    */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    /*Con este metodo medimos la cantidad
    **de reservas canceladas o completadas
    */
    public StatusReservation reporteStatusServicio() {
        List<Reservation> completed = reservationRepository.ReservacionStatusRepositorio("completed");
        List<Reservation> cancelled = reservationRepository.ReservacionStatusRepositorio("cancelled");

        return new StatusReservation(completed.size(), cancelled.size());
    }
    /*Este metodo lo usamos para medir la cantidad de reservas
    **en un lapso de tiempo determinado
    */ 
    public List<Reservation> reporteTiempoServicio(String datoA, String datoB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        Date datoUno = new Date();
        Date datoDos = new Date();

        try {
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        } catch (ParseException evt) {
            evt.printStackTrace();
        }
        if (datoUno.before(datoDos)) {
            return reservationRepository.ReservacionTiempoRepositorio(datoUno, datoDos);
        } else {
            return new ArrayList<>();
        }
    }
    /*Con este metodo contamos las reservas
    **por cliente
    */
    public List<CountClient> reporteClientesServicio() {
        return reservationRepository.getClientesRepositorio();
    }

}
