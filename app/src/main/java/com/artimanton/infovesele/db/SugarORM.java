package com.artimanton.infovesele.db;

import com.artimanton.InfoVesele;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

public class SugarORM {
    public static void deleteTables(){
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(InfoVesele.getContext());
        schemaGenerator.deleteTables(new SugarDb(InfoVesele.getContext()).getDB());
        SugarContext.init(InfoVesele.getContext());
        schemaGenerator.createDatabase(new SugarDb(InfoVesele.getContext()).getDB());
    }


}
