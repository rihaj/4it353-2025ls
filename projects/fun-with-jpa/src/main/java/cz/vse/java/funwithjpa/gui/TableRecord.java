package cz.vse.java.funwithjpa.gui;

import java.math.BigDecimal;
import java.util.Map;

public class TableRecord {
    public static final Map<String, String> COLUMNS = Map.of("login", "Login", "name", "Name", "amount", "Amount");

    private String login;
    private String name;
    private BigDecimal amount;

    public TableRecord() {
    }

    public TableRecord(final String login, final String name, final BigDecimal amount) {
        this.login = login;
        this.name = name;
        this.amount = amount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

}
