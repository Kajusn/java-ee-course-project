package org.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Computer.findAll", query = "select a from Computer as a")
})
@Table(name = "COMPUTER")
@Getter @Setter
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="MANUFACTURER_ID")
    private Manufacturer manufacturer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equals(id, computer.id) &&
                Objects.equals(name, computer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Computer() {
    }

    public Computer(String name) {
        this.name = name;
    }
}
