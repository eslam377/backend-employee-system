package com.example.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomModelMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(final List<T> entityList, Class<D> outClass) {
        return entityList.stream().map(t->map(t,outClass)).collect(Collectors.toList());
    }


}
