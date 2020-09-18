package com.reactive.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.reactive.spring.model.Reservation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservationServiceImpl  implements ReservationService{
	
	private final ReactiveMongoOperations reactiveMongoOperations;
	
	@Autowired
	public ReservationServiceImpl(ReactiveMongoOperations reactiveMongoOperations) {
		this.reactiveMongoOperations = reactiveMongoOperations;
	}

	@Override
	public Mono<Reservation> getRservation(String id) {
		return reactiveMongoOperations.findById(id, Reservation.class);
	}

	@Override
	public Mono<Reservation> createRservation(Mono<Reservation> reservation) {
		return reactiveMongoOperations.save(reservation);
	}

	@Override
	public Mono<Reservation> updateRservation(String id, Mono<Reservation> reservation) {
		return reservation.flatMap(reserv->reactiveMongoOperations.findAndModify(
				Query.query(Criteria.where("id").is(id)),
				Update.update("price", reserv.getPrice()),Reservation.class
				).flatMap(result->{
					result.setPrice(reserv.getPrice());
					return Mono.just(result);
				})
				);
	}

	@Override
	public Mono<Boolean> deleteRservation(String id) {
		return reactiveMongoOperations.remove(Query.query(Criteria.where("id").is(id)),Reservation.class)
				.flatMap(DeleteResult->Mono.just(DeleteResult.wasAcknowledged()));
				
	}

	@Override
	public Flux<Reservation> getAllResevation() {
		return reactiveMongoOperations.findAll(Reservation.class);
	
	}

}
