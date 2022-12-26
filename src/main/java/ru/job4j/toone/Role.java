package ru.job4j.toone;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;

@Data
@Entity
@Table(name = "j_role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;
}
