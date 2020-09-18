package com.reactive.spring.service;
import com.reactive.spring.model.Reservation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {
	Mono<Reservation> getRservation(String id);
	Mono<Reservation> createRservation(Mono<Reservation> reservation);
	Mono<Reservation> updateRservation(String id,Mono<Reservation> reservation);
	Mono<Boolean> deleteRservation(String id);
	Flux<Reservation> getAllResevation();

}
