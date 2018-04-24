package com.baluche.model.database.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.baluche.model.database.entity.Suggest;

import com.baluche.model.database.greendao.SuggestDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig suggestDaoConfig;

    private final SuggestDao suggestDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        suggestDaoConfig = daoConfigMap.get(SuggestDao.class).clone();
        suggestDaoConfig.initIdentityScope(type);

        suggestDao = new SuggestDao(suggestDaoConfig, this);

        registerDao(Suggest.class, suggestDao);
    }
    
    public void clear() {
        suggestDaoConfig.clearIdentityScope();
    }

    public SuggestDao getSuggestDao() {
        return suggestDao;
    }

}
