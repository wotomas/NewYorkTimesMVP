package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Generator {
    private static final String PROJECT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        Schema schema = new Schema(1, "info.kimjihyok.new_york_times_client.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, PROJECT_DIR + "/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        Entity postItem = addPostItem(schema);
        Entity multiMedia = addMultimedia(schema);
    }

    private static Entity addMultimedia(Schema schema) {
        Entity media = schema.addEntity("Multimedia");

        media.addStringProperty("url");
        media.addStringProperty("format");
        media.addStringProperty("height");
        media.addStringProperty("width");
        media.addStringProperty("type");
        media.addStringProperty("subtype");
        media.addStringProperty("caption");
        media.addStringProperty("copyright");
        return media;
    }

    private static Entity addPostItem(final Schema schema) {
        Entity postItem = schema.addEntity("PostItem");
        postItem.addIdProperty().primaryKey().autoincrement();
        postItem.addStringProperty("section");
        postItem.addStringProperty("subsection");

        postItem.addStringProperty("title");
        //abstract add in custom field
        //postItem.addStringProperty("abstract");
        postItem.addStringProperty("url");
        postItem.addStringProperty("byline");
        postItem.addStringProperty("updated_date");
        postItem.addStringProperty("created_date");
        postItem.addStringProperty("published_date");

        postItem.addStringProperty("material_type_facet");
        postItem.addStringProperty("kicker");
        postItem.addStringProperty("des_facet");
        postItem.addStringProperty("org_facet");
        postItem.addStringProperty("per_facet");
        postItem.addStringProperty("geo_facet");

        return postItem;
    }


}
