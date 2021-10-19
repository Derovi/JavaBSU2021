package processed_collections;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import by.derovi.service_monitoring.visualizer.Table;

public class TableViewCollection<T> implements  FinalProcessedCollection<T, Table> {
    public static class ColumnProvider<T> {
        private ColumnProvider(String title, Function<T, ?> fieldExtractor) {
        this.title = title;
        this.fieldExtractor = fieldExtractor;
        }

        public String getTitle() {
            return title;
        }

        public String provideCell(T element) {
            return fieldExtractor.apply(element).toString();
        }

        public static <T, E> ColumnProvider<T> of(String title, Function<T, ?> fieldExtractor)  {
            return new ColumnProvider<T>(title, fieldExtractor);
        }

        private String title;
        private Function<T, ?> fieldExtractor;
    }

    public TableViewCollection(String tableName, List<ColumnProvider<T>> colomnProviders) {
        this.tableName = tableName;
        this.colomnProviders = colomnProviders;
    }

    @Override
    public void renew(Collection<? extends T> elements) {
        table = new Table(tableName);
        table.addRow(colomnProviders.stream().map(ColumnProvider::getTitle).collect(Collectors.toList()));
        table.addRows(elements.stream().map(element -> colomnProviders.stream().map(provider -> provider.provideCell(element)).collect(Collectors.toList())).collect(Collectors.toList()));
    }

    @Override
    public Table currentState() {
        return table;
    }

    private String tableName;
    private List<ColumnProvider<T>> colomnProviders;
    private Table table;
}
