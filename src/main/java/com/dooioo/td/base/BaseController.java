package com.dooioo.td.base;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础Controller
 * <ul>
 *     <li>异常捕获</li>
 *     <li>返回定义</li>
 * </ul>
 *
 * @author huang.peijie
 * @since 2015/11/29 00:19
 */
public abstract class BaseController {

    protected Logger log = LoggerFactory.getLogger(getClass());

    // ---------- 异常捕获 ----------

    @ExceptionHandler(AppException.class)
    public ResponseEntity handleAppException(AppException ex) {
        if (ex.getCode() == AppException.CodeEnum.无权限.code) {
            return unauthorized().code(ex.getCode()).message(ex.getMessage()).build();
        } else {
            return badRequest().code(ex.getCode()).message(ex.getMessage()).build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        Throwable t = ex;
        while (true) {
            if ((t = t.getCause()) == null) break;
            if (t instanceof AppException) {
                return handleAppException((AppException) t);
            }
        }
        log.error("", ex);
        return internalServerError().code(AppException.CodeEnum.未知.code).message("服务器内部错误").build();
    }

    // ---------- 返回定义 ----------

    /**
     * 操作正常返回
     *
     * @return
     */
    public Result ok() {
        return new Result(HttpStatus.OK);
    }

    /**
     * 操作失败（参数异常、校验失败）
     *
     * @return
     */
    public Result badRequest() {
        return new Result(HttpStatus.BAD_REQUEST);
    }

    /**
     * 操作失败（权限异常）
     *
     * @return
     */
    public Result unauthorized() {
        return new Result(HttpStatus.UNAUTHORIZED);
    }

    /**
     * 未捕获的其他异常，程序bug
     *
     * @return
     */
    public Result internalServerError() {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Controller返回值，spring会自动转化为json，可以链式操作<br>
     * 1、put：键值对<br>
     * 2、body：直接返回javaBean<br>
     * 3、jsonp：callback返回javaBean<br>
     * 3种方式不能同时使用（程序不做判断，仅作为约定）
     */
    public class Result {

        private Map<String, Object> map;
        private Object body;
        private String callback;
        private HttpStatus httpStatus;

        public Result(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }

        /**
         * 键值对
         *
         * @param key
         * @param value
         * @return
         */
        public Result put(String key, Object value) {
            if (map == null) map = new HashMap<String, Object>();
            map.put(key, value);
            return this;
        }

        /**
         * 键值对，异常代码
         *
         * @param code
         * @return
         */
        public Result code(int code) {
            return put("code", code);
        }

        /**
         * 键值对，异常描述
         *
         * @param message
         * @return
         */
        public Result message(String message) {
            return put("message", message);
        }

        /**
         * 直接返回javaBean
         *
         * @param body
         * @return
         */
        public Result body(Object body) {
            this.body = body;
            return this;
        }

        /**
         * callback返回javaBean
         *
         * @param body
         * @return
         */
        public Result jsonp(String callback, Object body) {
            this.callback = callback;
            this.body = body;
            return this;
        }

        public ResponseEntity build() {
            if (callback != null && body != null)
                return ResponseEntity.status(httpStatus).body(callback + "(" + JSON.toJSONString(body) + ")");
            if (body != null) return ResponseEntity.status(httpStatus).body(body);
            return ResponseEntity.status(httpStatus).body(map);
        }
    }
}
