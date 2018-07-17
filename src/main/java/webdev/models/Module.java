package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
public class Module {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne
    @JsonIgnore
    private Course course;

}
