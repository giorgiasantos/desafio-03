package com.example.catalisa.desafio3.repository;

import com.example.catalisa.desafio3.model.VendasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasRepository extends JpaRepository<VendasModel, Long> {
}
