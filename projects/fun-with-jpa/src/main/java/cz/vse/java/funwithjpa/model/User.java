package cz.vse.java.funwithjpa.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Long id;

    @Column(name = "ACTIVE", nullable = false)
    protected Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    protected UserType type;

    @Column(name = "LOGIN", nullable = false, unique = true)
    protected String login;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    protected LocalDate dateOfBirth;

    @Column(name = "BALANCE", nullable = false)
    protected BigDecimal balance;

    @Column(name = "LAST_LOGIN")
    protected LocalDateTime lastLogin;

    public Long getId() {
        return id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public void setActive(boolean active) {
        this.active = Boolean.valueOf(active);
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", active=" + active +
                ", type=" + type +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", balance=" + balance +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
