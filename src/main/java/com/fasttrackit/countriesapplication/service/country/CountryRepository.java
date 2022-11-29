package com.fasttrackit.countriesapplication.service.country;

import com.fasttrackit.countriesapplication.model.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByContinent(String name);

    @Query("select c from Country c where c.continent=:name")
    List<Country> findByContinentByQuery(@Param("name") String name);

//    @Query(value = "select * from COUNTRY where continent=:name", nativeQuery = true)
//    List<Country> findByContinentByNativeQuery(@Param("name") String name);
}
