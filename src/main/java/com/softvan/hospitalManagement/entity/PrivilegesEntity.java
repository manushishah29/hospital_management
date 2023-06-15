package com.softvan.hospitalManagement.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "privilege_tbl")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegesEntity extends BaseEntityAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "privilege_name")
    private String privilegeName;

}
