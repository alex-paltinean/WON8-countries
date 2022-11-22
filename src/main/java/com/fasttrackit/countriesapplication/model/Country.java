package com.fasttrackit.countriesapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Country {
    //name|capital|population|area|continent|neighbour1~neighbour2
    private long id;
    private String name;
    private String capital;
    private long population;
    private int area;
    private String continent;
    private List<String> neighbours;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
