package cz.vse.java.funwithjpa.controller;

import cz.vse.java.funwithjpa.gui.TableRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class Controller {
    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    public void reloadData(String filterValue, List<TableRecord> tableData) {
        LOG.info("Reloading records, filter applied to login: '{}'.", filterValue);

        tableData.clear();
        tableData.add(new TableRecord("Fake login", "Fake name", BigDecimal.valueOf(123.5)));
    }

    public void insertNewRecord(List<TableRecord> tableData) {
        LOG.info("Inserting new record.");

        tableData.add(new TableRecord("New login", "New name", BigDecimal.valueOf(567.8)));
    }

}
