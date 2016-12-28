package com.ssh.domain;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Administrator on 2016/12/28.
 */
@Entity
@Table(name = "tb_authgroup")
public class Authgroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //////////////////////////////////////////////
    @ManyToMany(mappedBy = "authgroups",cascade = CascadeType.ALL)
    private Set<Role> roles=new HashSet<Role>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
