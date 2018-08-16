package webdev.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import webdev.models.Course;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.ModuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ModuleService {
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    CourseRepository courseRepository;

    /**
     * createModule
     * creates a module for a course
     * POST /api/course/{cid}/module
     **/
    @CrossOrigin(origins = "*")
    @PostMapping("/api/course/{cid}/module")
    public Module createModule(@PathVariable("cid") int cId, @RequestBody Module mod) {
        Optional<Course> coursep = courseRepository.findById(cId);
        if (coursep.isPresent()) {
            Course c = coursep.get();
            mod.setCourse(c);
            moduleRepository.save(mod);
            return mod;
        }
        return null;
    }


    /**
     * deleteModule
     * deletes a module by id
     * DELETE /api/module/{id}
     **/
    @CrossOrigin(origins = "*")
    @DeleteMapping("/api/module/{id}")
    public Object deleteModule(@PathVariable("id") int id) {
        moduleRepository.deleteById(id);
        return null;
    }

    /**
     * findAllModules
     * retrieves all the modules
     * GET /api/module
     **/
    @CrossOrigin(origins = "*")
    @GetMapping("/api/module")
    public List<Module> findAllModules() {
        return (List<Module>) moduleRepository.findAll();
    }

    /**
     * findModuleById
     * retrieves a module by id
     * GET /api/module/{id}
     **/
    @CrossOrigin(origins = "*")
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
     * findAllModulesForCourse
     * retrieves all modules for course
     * GET /api/course/{cid}/module
     **/
    @CrossOrigin(origins = "*")
    @GetMapping("/api/course/{cid}/module")
    public List<Module> findAllModulesForCourse(@PathVariable("cid") int cId) {
        try {
            Optional<Course> coursep = courseRepository.findById(cId);
            System.out.println("Retrieved an optional?");
            if (coursep.isPresent()) {
                Course c = coursep.get();
                System.out.println("Retrieved course");
                return c.getModules();
            }
        } catch (Exception e) {
          System.out.println(e);
        }
        return new ArrayList<Module>();
    }


    /**
     * updateModule
     * updates a module by id
     * PUT /api/module/{id}
     **/
    @CrossOrigin(origins = "*")
    @PutMapping("/api/module/{id}")
    public Module updateModule(@PathVariable("id") int id, @RequestBody Module mod) {
        Optional<Module> m = moduleRepository.findById(id);
        if (m.isPresent()) {
            Module oldMod = m.get();
            oldMod.setTitle(mod.getTitle());
            return moduleRepository.save(oldMod);
        }
        return null;
    }

}
