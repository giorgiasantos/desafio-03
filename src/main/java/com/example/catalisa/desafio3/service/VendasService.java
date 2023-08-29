package com.example.catalisa.desafio3.service;

import com.example.catalisa.desafio3.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendasService {
    @Autowired
    private VendasRepository vendasRepository;


}
