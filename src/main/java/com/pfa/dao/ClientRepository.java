package com.pfa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfa.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
