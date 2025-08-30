package com.maxvpire.rates.AppointmentRates;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface RatesRepository extends CassandraRepository<Rates, String> {
}
