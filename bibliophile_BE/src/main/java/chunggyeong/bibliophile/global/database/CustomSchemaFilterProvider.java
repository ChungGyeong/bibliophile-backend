package chunggyeong.bibliophile.global.database;

import org.hibernate.tool.schema.spi.SchemaFilter;
import org.hibernate.tool.schema.spi.SchemaFilterProvider;

public class CustomSchemaFilterProvider implements SchemaFilterProvider {

    @Override
    public SchemaFilter getCreateFilter() {
        return new CustomSchemaFilter();
    }

    @Override
    public SchemaFilter getDropFilter() {
        return new CustomSchemaFilter();
    }

    @Override
    public SchemaFilter getTruncatorFilter() {
        return null;
    }

    @Override
    public SchemaFilter getMigrateFilter() {
        return new CustomSchemaFilter();
    }

    @Override
    public SchemaFilter getValidateFilter() {
        return new CustomSchemaFilter();
    }
}
