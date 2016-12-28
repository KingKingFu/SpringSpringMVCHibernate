package com.ssh.domain;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2016/12/28.
 */
@Entity
@Table(name = "tb_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
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

    ////////////////////////////////////////////////////////////
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_role_authgroup",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "authgroup_id")})
    private Set<Authgroup> authgroups=new HashSet<Authgroup>();

    public Set<Authgroup> getAuthgroups() {
        return authgroups;
    }

    public void setAuthgroups(Set<Authgroup> authgroups) {
        this.authgroups = authgroups;
    }

    ///////////////////////////////////////////////////////
    @ElementCollection
    @JoinTable(name = "t_set",joinColumns = {@JoinColumn(name = "role_id")})
    @Column(name = "name")//集合映射
    private Set<String> names;

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }

    ///////////////////////////////////////////////////////////////////////////
    @ElementCollection
    @JoinTable(name = "t_list",joinColumns = {@JoinColumn(name = "role_id")})
    @Column(name = "list")
    @IndexColumn(name = "list_index")
    private List<String> list=new ArrayList<String>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    /////////////////////////////////////////////////////////////////////////
    @ElementCollection
    @JoinTable(name = "tb_map",joinColumns = {@JoinColumn(name = "role_id") })
    @Column(name = "map")
    @MapKeyColumn(name = "map_key")
    private Map<String,String> map=new HashMap<String, String>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
