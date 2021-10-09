package com.neueda.assignment.common;

import java.util.List;

public interface Mapper <T, U> {
    T mapToDTO(U u);
    U mapToEntity(T t);

    List<T> mapListEntityToListDTO(List<U> u);
    List<U> mapListDTOToListEntity(List<T> t);

}