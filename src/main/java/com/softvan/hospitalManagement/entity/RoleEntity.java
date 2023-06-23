package com.softvan.hospitalManagement.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_tbl")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntityAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;
}
