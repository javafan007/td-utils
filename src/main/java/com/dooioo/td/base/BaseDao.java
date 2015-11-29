package com.dooioo.td.base;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * 基础DAO：简化SqlSession，只保留必要的CRUD操作
 *
 * @author huang.peijie
 * @since 2015/11/28 23:43
 */
public class BaseDao extends SqlSessionDaoSupport {
    public <T> T selectOne(String statement, Object parameter) {
        return getSqlSession().selectOne(statement, parameter);
    }

    public <E> List<E> selectList(String statement, Object parameter) {
        return getSqlSession().selectList(statement, parameter);
    }

    public int insert(String statement, Object parameter) {
        return getSqlSession().insert(statement, parameter);
    }

    public int update(String statement, Object parameter) {
        return getSqlSession().update(statement, parameter);
    }

    public int delete(String statement, Object parameter) {
        return getSqlSession().delete(statement, parameter);
    }
}
