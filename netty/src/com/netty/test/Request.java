package com.netty.test;

import java.io.Serializable;

public class Request implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 3701941641993894303L;
    @SuppressWarnings("rawtypes")
    private Class service; // 接口类
    private String method; // 调用方法名称
    private Object[] paras; // 调用方法参数
    private String version; // 服务版本

    /**
     * @return the service
     */
    @SuppressWarnings("rawtypes")
    public Class getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Class service) {
        this.service = service;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the paras
     */
    public Object[] getParas() {
        return paras;
    }

    /**
     * @param paras the paras to set
     */
    public void setParas(Object[] paras) {
        this.paras = paras;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }
}