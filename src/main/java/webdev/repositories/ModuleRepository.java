package webdev.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.Module;


public interface ModuleRepository extends CrudRepository<Module, Integer> {

}