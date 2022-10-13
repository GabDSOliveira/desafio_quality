package com.grupo2.desafioquality.repository;

import com.grupo2.desafioquality.model.District;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DistrictRepository {
    private final List<District> districts = new ArrayList<>();
}
