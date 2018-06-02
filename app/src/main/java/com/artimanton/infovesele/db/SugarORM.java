package com.artimanton.infovesele.db;

import com.artimanton.infoVesele;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

public class SugarORM {
    public static void deleteTables(){
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(infoVesele.getContext());
        schemaGenerator.deleteTables(new SugarDb(infoVesele.getContext()).getDB());
        SugarContext.init(infoVesele.getContext());
        schemaGenerator.createDatabase(new SugarDb(infoVesele.getContext()).getDB());
    }


}
