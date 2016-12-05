package info.kimjihyok.new_york_times_client.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import info.kimjihyok.new_york_times_client.db.PostItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "POST_ITEM".
*/
public class PostItemDao extends AbstractDao<PostItem, Long> {

    public static final String TABLENAME = "POST_ITEM";

    /**
     * Properties of entity PostItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Section = new Property(1, String.class, "section", false, "SECTION");
        public final static Property Subsection = new Property(2, String.class, "subsection", false, "SUBSECTION");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Url = new Property(4, String.class, "url", false, "URL");
        public final static Property Byline = new Property(5, String.class, "byline", false, "BYLINE");
        public final static Property Updated_date = new Property(6, String.class, "updated_date", false, "UPDATED_DATE");
        public final static Property Created_date = new Property(7, String.class, "created_date", false, "CREATED_DATE");
        public final static Property Published_date = new Property(8, String.class, "published_date", false, "PUBLISHED_DATE");
        public final static Property Material_type_facet = new Property(9, String.class, "material_type_facet", false, "MATERIAL_TYPE_FACET");
        public final static Property Kicker = new Property(10, String.class, "kicker", false, "KICKER");
    };


    public PostItemDao(DaoConfig config) {
        super(config);
    }
    
    public PostItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"POST_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SECTION\" TEXT," + // 1: section
                "\"SUBSECTION\" TEXT," + // 2: subsection
                "\"TITLE\" TEXT," + // 3: title
                "\"URL\" TEXT," + // 4: url
                "\"BYLINE\" TEXT," + // 5: byline
                "\"UPDATED_DATE\" TEXT," + // 6: updated_date
                "\"CREATED_DATE\" TEXT," + // 7: created_date
                "\"PUBLISHED_DATE\" TEXT," + // 8: published_date
                "\"MATERIAL_TYPE_FACET\" TEXT," + // 9: material_type_facet
                "\"KICKER\" TEXT);"); // 10: kicker
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"POST_ITEM\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PostItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String section = entity.getSection();
        if (section != null) {
            stmt.bindString(2, section);
        }
 
        String subsection = entity.getSubsection();
        if (subsection != null) {
            stmt.bindString(3, subsection);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(5, url);
        }
 
        String byline = entity.getByline();
        if (byline != null) {
            stmt.bindString(6, byline);
        }
 
        String updated_date = entity.getUpdated_date();
        if (updated_date != null) {
            stmt.bindString(7, updated_date);
        }
 
        String created_date = entity.getCreated_date();
        if (created_date != null) {
            stmt.bindString(8, created_date);
        }
 
        String published_date = entity.getPublished_date();
        if (published_date != null) {
            stmt.bindString(9, published_date);
        }
 
        String material_type_facet = entity.getMaterial_type_facet();
        if (material_type_facet != null) {
            stmt.bindString(10, material_type_facet);
        }
 
        String kicker = entity.getKicker();
        if (kicker != null) {
            stmt.bindString(11, kicker);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public PostItem readEntity(Cursor cursor, int offset) {
        PostItem entity = new PostItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // section
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // subsection
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // url
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // byline
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // updated_date
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // created_date
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // published_date
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // material_type_facet
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // kicker
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PostItem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSection(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSubsection(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setByline(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUpdated_date(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCreated_date(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPublished_date(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMaterial_type_facet(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setKicker(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(PostItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(PostItem entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
