package com.fasttrackit.countriesapplication.model.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Country {
    //name|capital|population|area|continent|neighbour1~neighbour2
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Column
    private String capital;
    @Column
    private long population;
    @Column
    private int area;
    @Column
    private String continent;
    @Transient
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
