package com.gy.myframework.Service.filter;

/**
 * Created by gy on 2016/3/26.
 */
public interface IFilterEntity<T> {
    public T doFilter(T t)throws Exception;
}
