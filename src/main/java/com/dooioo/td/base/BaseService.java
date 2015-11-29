package com.dooioo.td.base;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * 基础Service
 * <ul>
 *     <li>包含根据泛型找到对应的mybatis xml的DAO</li>
 * </ul>
 *
 * @author huang.peijie
 * @since 2015/11/29 00:18
 */
public abstract class BaseService<T> {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseDao realDao;
    protected BaseDao dao;

    private String namespace;

    public BaseService() {
        initNamespace();
        initDAO();
    }

    /**
     * 找到直接继承BaseService的类获取泛型
     */
    private void initNamespace() {
        Class targetClazz = getClass();
        while (true) {
            if (targetClazz.getSuperclass().equals(BaseService.class)) {
                break;
            }
            targetClazz = targetClazz.getSuperclass();
        }

        Class<T> clazz = (Class<T>) ((ParameterizedType) targetClazz.getGenericSuperclass()).getActualTypeArguments()[0];
        namespace = clazz.getSimpleName();
    }

    /**
     * 动态代理dao，使用dao不在需要sqlId
     */
    private void initDAO() {
        Enhancer e = new Enhancer();
        e.setSuperclass(BaseDao.class);
        e.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object retValFromSuper = null;
                try {
                    // 根据namespace组装mybatis的sqlId
                    args[0] = new StringBuilder(namespace).append(".").append(args[0]).toString();
                    retValFromSuper = method.invoke(realDao, args);
                } catch (Throwable t) {
                    throw t.fillInStackTrace();
                }
                return retValFromSuper;
            }
        });
        dao = (BaseDao) e.create();
    }
}
