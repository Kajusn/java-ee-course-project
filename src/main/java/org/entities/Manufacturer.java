package org.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Manufacturer.findAll", query = "select a from Manufacturer as a")
})
@Table(name = "MANUFACTURER")
@Getter @Setter
public class Manufacturer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<Computer> computers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer manufacturer = (Manufacturer) o;
        return Objects.equals(id, manufacturer.id) &&
                Objects.equals(name, manufacturer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }
}
