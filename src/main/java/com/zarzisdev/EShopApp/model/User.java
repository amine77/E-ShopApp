package com.zarzisdev.EShopApp.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;


import org.hibernate.annotations.NaturalId;
import sun.misc.BASE64Encoder;

/**
 * @author Amine
 */

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_LOGIN, query = "SELECT c FROM User c WHERE c.username = :username"),
        @NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT c FROM User c WHERE c.email = :email"),
        @NamedQuery(name = User.FIND_BY_LOGIN_PASSWORD, query = "SELECT c FROM User c WHERE c.username = :username AND c.password = :password"),
        @NamedQuery(name = User.FIND_BY_UUID, query = "SELECT c FROM User c WHERE c.uuid = :uuid"),
        @NamedQuery(name = User.FIND_ALL, query = "SELECT c FROM User c")
})
@XmlRootElement
public class User implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @Column(length = 50, name = "first_name", nullable = false)
    @NotNull
    @Size(min = 3, max = 50)
    private String firstName;

    @Column(length = 50, name = "last_name", nullable = false)
    @NotNull
    @Size(min = 3, max = 50)
    private String lastName;

    @Column
    private String telephone;

    @Column
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(length = 10, nullable = false)
    private String username;

    @Column(length = 256, nullable = false)
    @NotNull
    @Size(min = 6, max = 256)
    private String password;

    @Column(length = 256)
    @Size(min = 1, max = 256)
    private String uuid;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @Past
    private Date dateOfBirth;

    @Transient
    private Integer age;

    @Embedded
    @Valid
    private Address homeAddress = new Address();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column
    private boolean enabled;

    // ======================================
    // = Constants =
    // ======================================

    public static final String FIND_BY_LOGIN = "User.findByUsername";
    public static final String FIND_BY_LOGIN_PASSWORD = "User.findByUsernameAndPassword";
    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_EMAIL = "User.findByEmail";
    public static final String FIND_BY_UUID = "User.findByUUID";

    // ======================================
    // = Constructors =
    // ======================================

    public User() {
    }

    public User(String firstName, String lastName, String username, String plainTextPassword, String email,
                Address address, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = digestPassword(plainTextPassword);
        this.email = email;
        this.homeAddress = address;
        this.dateOfBirth = new Date();
        this.enabled = enabled;
    }

    // ======================================
    // = Lifecycle Methods =
    // ======================================

    /**
     * This method calculates the age of the user
     */
    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateAge() {
        if (dateOfBirth == null) {
            age = null;
            return;
        }

        Calendar birth = new GregorianCalendar();
        birth.setTime(dateOfBirth);
        Calendar now = new GregorianCalendar();
        now.setTime(new Date());
        int adjust = 0;
        if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
            adjust = -1;
        }
        age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust;
    }

    @PrePersist
    private void digestPassword() {
        password = digestPassword(password);
    }

    // ======================================
    // = Business methods =
    // ======================================

    /**
     * Digest password with <code>SHA-256</code> then encode it with Base64.
     *
     * @param plainTextPassword the password to digest and encode
     * @return digested password
     */
    public String digestPassword(String plainTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            return new BASE64Encoder().encode(passwordDigest);
        } catch (Exception e) {
            throw new RuntimeException("Exception encoding password", e);
        }
    }

    // ======================================
    // = Getters & setters =
    // ======================================

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // ======================================
    // = Methods hash, equals, toString =
    // ======================================

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName + " (" + username + ")";
    }
}
