package com.testeXbrain.testeXbrain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_vendedor")
@Entity
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Long id;
    private String nome;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private Collection<Venda> vendas;
}
