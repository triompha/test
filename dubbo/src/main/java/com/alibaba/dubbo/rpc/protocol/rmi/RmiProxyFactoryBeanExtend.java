package com.alibaba.dubbo.rpc.protocol.rmi;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.RemoteInvocationFailureException;
import org.springframework.remoting.rmi.RmiClientInterceptorUtils;
import org.springframework.remoting.rmi.RmiInvocationHandler;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.support.RemoteInvocationUtils;



public class RmiProxyFactoryBeanExtend extends RmiProxyFactoryBean {
	private static final Log logger = LogFactory.getLog(RmiProxyFactoryBeanExtend.class);
    /**
     * Perform the given invocation on the given RMI stub.
     * @param invocation the AOP method invocation
     * @param stub the RMI stub to invoke
     * @return the invocation result, if any
     * @throws Throwable in case of invocation failure
     */
    @Override
    protected Object doInvoke(MethodInvocation invocation, Remote stub) throws Throwable {
        if (stub instanceof RmiInvocationHandler) {
            // RMI invoker
            try {

                return doInvoke(invocation, (RmiInvocationHandler) stub);
            }
            catch (RemoteException ex) {
                logger.warn("doInvoke:"+ex,ex);
                throw RmiClientInterceptorUtils.convertRmiAccessException(
                    invocation.getMethod(), ex, isConnectFailure(ex), getServiceUrl());
            }
            catch (InvocationTargetException ex) {
                logger.warn("doInvoke:"+ex,ex);
                Throwable exToThrow = ex.getTargetException();
                RemoteInvocationUtils.fillInClientStackTraceIfPossible(exToThrow);
                throw exToThrow;
            }
            catch (Throwable ex) {
                logger.warn("doInvoke:"+ex,ex);
                throw new RemoteInvocationFailureException("Invocation of method [" + invocation.getMethod() +
                        "] failed in RMI service [" + getServiceUrl() + "]", ex);
            }
        }
        else {
            // traditional RMI stub
            try {

                return RmiClientInterceptorUtils.invokeRemoteMethod(invocation, stub);
            }
            catch (InvocationTargetException ex) {
                logger.warn("doInvoke:"+ex,ex);
                Throwable targetEx = ex.getTargetException();
                if (targetEx instanceof RemoteException) {
                    logger.warn("doInvoke:"+ex,ex);
                    RemoteException rex = (RemoteException) targetEx;
                    throw RmiClientInterceptorUtils.convertRmiAccessException(
                            invocation.getMethod(), rex, isConnectFailure(rex), getServiceUrl());
                }
                else {
                    logger.warn("doInvoke:"+ex,ex);
                    this.refreshAndRetry(invocation);
                    
                    throw targetEx;
                }
            }
            catch (Throwable ex) {
                logger.warn("doInvoke:"+ex,ex);
                throw new RemoteInvocationFailureException("Invocation of method [" + invocation.getMethod() +
                        "] failed in RMI service [" + getServiceUrl() + "]", ex);
            }
        }
    }
}
