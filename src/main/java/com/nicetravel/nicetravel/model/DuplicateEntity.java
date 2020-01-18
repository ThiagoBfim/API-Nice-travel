package com.nicetravel.nicetravel.model;

/**
 * As implementações dessa classe deverão permitir que o objeto em questão seja duplicado.
 *
 * @param <T> Parametro que deverá receber para realizar o duplicate
 */
public interface DuplicateEntity<T> {

    DuplicateEntity duplicate(T entity);
}

