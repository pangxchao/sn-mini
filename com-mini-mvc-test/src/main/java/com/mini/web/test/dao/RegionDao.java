package com.mini.web.test.dao;

import com.google.inject.ImplementedBy;
import com.mini.jdbc.SQLBuilder;
import com.mini.web.test.dao.base.BaseRegionDao;
import com.mini.web.test.dao.impl.RegionDaoImpl;
import com.mini.web.test.entity.Region;
import com.mini.web.test.entity.mapper.RegionMapper;

import java.util.List;

import static com.mini.web.test.entity.Region.REGION_ID;

/**
 * RegionDao.java
 * @author xchao
 */
@ImplementedBy(RegionDaoImpl.class)
public interface RegionDao extends BaseRegionDao {
    default List<Region> queryByParent(long parentId) {
        return query(new SQLBuilder() {{
            RegionMapper.init(this);
            where(REGION_ID + " = ?");
            params(parentId);
        }}, getRegionMapper());
    }
}