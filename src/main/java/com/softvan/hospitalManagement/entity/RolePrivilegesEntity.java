package com.softvan.hospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_privilege_mapping_tbl")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RolePrivilegesEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    private PrivilegesEntity privilege;
}
