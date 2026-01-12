package com.star.template.spi;

public interface Logger {

    public static final String NAME = "star-template";

    void trace(String msg);

    void trace(Throwable e);

    void trace(String msg, Throwable e);

    void debug(String msg);

    void debug(Throwable e);

    void debug(String msg, Throwable e);

    void info(String msg);

    void info(Throwable e);

    void info(String msg, Throwable e);

    void warn(String msg);

    void warn(Throwable e);

    void warn(String msg, Throwable e);

    void error(String msg);

    void error(Throwable e);

    void error(String msg, Throwable e);

    boolean isTraceEnabled();

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    boolean isErrorEnabled();

}