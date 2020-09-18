package com.reactive.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.spring.model.Reservation;
import com.reactive.spring.service.ReservationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("room/v1/reservation/")
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@CrossOrigin
public class ReservationResource {
	public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation/";
	
	private final ReservationService reservationService;
	
	@Autowired
	public ReservationResource(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	
	@GetMapping(path="{id}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Mono<Reservation> getReservationById(@PathVariable String id){
		return reservationService.getRservation(id);
			//return Mono.just("{get}");
		}
	
	@PostMapping(path="",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation){
		return reservationService.createRservation(reservation);
		
	}
	
	@PutMapping(path="{id}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mono<Reservation> updatePrice(@PathVariable String  id,@RequestBody Mono<Reservation> reservation){
		return reservationService.updateRservation(id, reservation);
	}
	
	@DeleteMapping(path="{id}")
	public Mono<Boolean> deletePrice(@PathVariable String id){
		
        return reservationService.deleteRservation(id);
	}
		
	@GetMapping
	public Flux<Reservation> listAllReservatio(){
		  return reservationService.getAllResevation();
	}
	}
	
