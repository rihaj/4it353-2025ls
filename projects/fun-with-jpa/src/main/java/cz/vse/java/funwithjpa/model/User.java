package cz.vse.java.funwithjpa.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "ACTIVE")
    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private UserType type;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", active=" + active +
                ", type=" + type +
                ", login='" + login + "'" +
                ", name='" + name + "'" +
                ", dateOfBirth=" + dateOfBirth +
                ", balance=" + balance +
                ", lastLogin=" + lastLogin +
                ", version=" + version +
                ']';
    }
}
