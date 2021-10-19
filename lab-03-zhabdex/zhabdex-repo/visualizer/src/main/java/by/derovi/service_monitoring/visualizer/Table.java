package by.derovi.service_monitoring.visualizer;

import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Table {
    private String title;
    private List<List<String>> rows;

    public Table(String title) {
        this.title = title;
        this.rows = new ArrayList<>();
    }

    public Table addRows(List<List<String>> rows) {
        this.rows.addAll(rows);
        return this;
    }

    public Table addRow(String... values) {
        rows.add(Arrays.asList(values));
        return this;
    }

    public Table addRow(List<String> values) {
        rows.add(values);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public List<List<String>> getRows() {
        return rows;
    }
}