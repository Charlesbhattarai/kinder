package com.seva.kinder.entity;
import com.seva.kinder.service.PersonService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="Person")
public class Person implements UserDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private String age;

    @NotNull(message="this value could not be null !!!")
    private String fullName;

    @Size(max=100)
    private String address;


    private LocalDate dob;

    @NotNull
    private String email;

    @NotNull
    private String role;

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         PersonService personService=null;
        Set<GrantedAuthority> authorites = new HashSet<>();
        authorites.add(new Authority(getRole()));
//        personService.getAll().forEach(ur -> authorites.add(new Authority(ur.getRole())));
        return authorites;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
