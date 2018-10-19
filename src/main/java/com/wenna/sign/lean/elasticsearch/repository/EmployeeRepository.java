package com.wenna.sign.lean.elasticsearch.repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


import com.wenna.sign.lean.elasticsearch.pojo.Employee;


/**
 * 操作es
 */
@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {
    Employee queryEmployeeById(String id);

}
