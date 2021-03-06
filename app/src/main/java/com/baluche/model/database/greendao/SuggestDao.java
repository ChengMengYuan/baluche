package com.baluche.model.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.baluche.model.database.entity.Suggest;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SUGGEST".
*/
public class SuggestDao extends AbstractDao<Suggest, Void> {

    public static final String TABLENAME = "SUGGEST";

    /**
     * Properties of entity Suggest.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Net_park_id = new Property(0, String.class, "net_park_id", false, "NET_PARK_ID");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Address = new Property(2, String.class, "address", false, "ADDRESS");
    }


    public SuggestDao(DaoConfig config) {
        super(config);
    }
    
    public SuggestDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SUGGEST\" (" + //
                "\"NET_PARK_ID\" TEXT," + // 0: net_park_id
                "\"TITLE\" TEXT," + // 1: title
                "\"ADDRESS\" TEXT);"); // 2: address
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SUGGEST\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Suggest entity) {
        stmt.clearBindings();
 
        String net_park_id = entity.getNet_park_id();
        if (net_park_id != null) {
            stmt.bindString(1, net_park_id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(3, address);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Suggest entity) {
        stmt.clearBindings();
 
        String net_park_id = entity.getNet_park_id();
        if (net_park_id != null) {
            stmt.bindString(1, net_park_id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(3, address);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Suggest readEntity(Cursor cursor, int offset) {
        Suggest entity = new Suggest( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // net_park_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // address
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Suggest entity, int offset) {
        entity.setNet_park_id(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAddress(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Suggest entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Suggest entity) {
        return null;
    }

    @Override
    public boolean hasKey(Suggest entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
