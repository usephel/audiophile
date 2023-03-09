package com.audiophileproject.usermanagement.models;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Role {
    @Id
    @SequenceGenerator(
        name= "role_gen",
        allocationSize=1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "role_gen"
    )
    @Getter
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    @Getter
    @Setter
    private ERole name;
}
