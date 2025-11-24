package com.realestate.repository;

import com.realestate.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertyRepository {

    @Autowired
    private JdbcTemplate jdbc;

    private RowMapper<Property> mapper = (rs, row) -> {
        Property p = new Property();
        p.setId(rs.getInt("id"));
        p.setTitle(rs.getString("title"));
        p.setType(rs.getString("type"));
        p.setPrice(rs.getDouble("price"));
        p.setSize(rs.getString("size"));
        p.setLocation(rs.getString("location"));
        p.setStatus(rs.getString("status"));
        p.setOwnerId((Integer) rs.getObject("owner_id"));
        return p;
    };

    public List<Property> findAll(){
        return jdbc.query("SELECT * FROM properties", mapper);
    }

    public int create(Property p){
        return jdbc.update(
            "INSERT INTO properties(title,type,price,size,location,status,owner_id) VALUES (?,?,?,?,?,?,?)",
            p.getTitle(), p.getType(), p.getPrice(), p.getSize(), p.getLocation(), p.getStatus(), p.getOwnerId()
        );
    }

    public int delete(int id){
        return jdbc.update("DELETE FROM properties WHERE id=?", id);
    }
}
