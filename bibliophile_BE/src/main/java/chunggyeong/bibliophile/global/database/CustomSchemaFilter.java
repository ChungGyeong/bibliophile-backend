package chunggyeong.bibliophile.global.database;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;

public class CustomSchemaFilter implements SchemaFilter {

    @Override
    public boolean includeNamespace(Namespace namespace) {
        return true;
    }

    @Override
    public boolean includeTable(Table table) {
        return !"book".equalsIgnoreCase(table.getName());
    }

    @Override
    public boolean includeSequence(Sequence sequence) {
        return true;
    }
}
