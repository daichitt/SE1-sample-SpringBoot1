package com.example.springboot1.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity
public class Employee {
//    @ManyToOne
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    //for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: jakarta.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'org.hibernate.validator.constraints.Length' validating type 'java.lang.Long'. Check configuration for 'id'] with root cause
//    @Length(min = 3, max = 30)

    private Long id;

    private String name;
    private int age;
    @NotEmpty
    private String image;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
