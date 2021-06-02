package com.pfa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfa.entities.GlobalAdmin;

public interface GlobalAdminRepository extends JpaRepository<GlobalAdmin, Long> {

}
