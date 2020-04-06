package org.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Processor.findAll", query = "select a from Processor as a")
})
@Table(name = "PROCESSOR")
@Getter
@Setter
public class Processor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "COMPUTER_PROCESSORS")
    private List<Computer> computers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return Objects.equals(id, processor.id) &&
                Objects.equals(name, processor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Processor() {
    }

    public Processor(String name) {
        this.name = name;
    }
}
