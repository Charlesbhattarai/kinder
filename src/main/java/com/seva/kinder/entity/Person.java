package com.seva.kinder.entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="Person")
public class Person {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private String age;

    @NotNull(message="this value could not be null !!!")
    private String fullName;

    @NotNull
    @Size(max=100)
    private String address;

    @NotNull
    private LocalDate dob;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "person")
    private Set<MobileDetail> mobile = new HashSet<>();



    public Set<MobileDetail> getMobile() {
        return mobile;
    }
    public void setMobile(Set<MobileDetail> mobile) {
        this.mobile = mobile;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }





    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(String dob) {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(!dob.equals("")) {
            this.dob=LocalDate.parse(dob, dateTimeFormatter);
        }

    }



}
