package webdev.services;

import org.springframework.web.bind.annotation.*;
import webdev.models.Module;
import webdev.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

public class ModuleService {
    ModuleRepository moduleRepository;

    /**
     * createModule
     * creates a module for a course
     * POST /api/course/{cid}/module
     **/
    @PostMapping("/api/course/{cid}/module")
    public Module createModule(@PathVariable("cid") int cId) {
        return null;
    }

    /**
     * deleteModule
     * deletes a module by id
     * DELETE /api/module/{id}
     **/
    @DeleteMapping("/api/module/{id}")
    public void deleteModule(@PathVariable("id") int id) {
        moduleRepository.deleteById(id);
    }

    /**
     * findAllModules
     * retrieves all the modules
     * GET /api/module
     **/
    @GetMapping("/api/module")
    public List<Module> findAllModules() {
        return (List<Module>) moduleRepository.findAll();
    }

    /**
     * findModuleById
     * retrieves a module by id
     * GET /api/module/{id}
     **/
    @GetMapping("/api/module/{id}")
    public Module findModuleById(@PathVariable("id") int id) {
        Optional<Module> c = moduleRepository.findById(id);
        if (c.isPresent()) {
            return c.get();
        } else {
            return null;
        }
    }

    /**
     findAllModulesForCourse
     retrieves all modules for course
     GET /api/course/{cid}/module
     **/

    /**
     * updateModule
     * updates a module by id
     * PUT /api/module/{id}
     **/
    @PutMapping("/api/module/{id}")
    public Module updateModule(@PathVariable("id") int id, @RequestBody Module mod) {
        Optional<Module> m = moduleRepository.findById(id);
        if (m.isPresent()) {
            Module oldMod = m.get();
            //modify old course
            return moduleRepository.save(oldMod);
        }
        return null;
    }

}
