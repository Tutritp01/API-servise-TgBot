package com.tutrit.sqldemo.repository;

import com.tutrit.sqldemo.entity.Engineer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EngineerRepository extends PagingAndSortingRepository<Engineer, Long> {
}
