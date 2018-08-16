package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ModuleRepository moduleRepository;

    /**
     * createLesson
     * creates a lesson for a course
     * POST /api/course/{cid}/lesson
     **/
    @CrossOrigin(origins = "*")
    @PostMapping("/api/course/{cid}/module/{mid}/lesson")
    public Lesson createLesson(@PathVariable(name = "mid") int mid, @RequestBody String lesson, @PathVariable String cid) {
        Optional<Module> optionalModule = moduleRepository.findById(mid);
        if (optionalModule.isPresent()) {
            Module m = optionalModule.get();
            List<Lesson> ls = m.getLessons();
            Lesson l = new Lesson();
            l.setTitle(lesson);
            l.setModule(m);
            ls.add(l);
            m.setLessons(ls);
            moduleRepository.save(m);
            lessonRepository.save(l);
            return l;
        }
        System.out.println("no module found");
        return null;
    }

    /**
     * deleteLesson
     * deletes a lesson by id
     * DELETE /api/lesson/{id}
     **/
    @CrossOrigin(origins = "*")
    @DeleteMapping("/api/lesson/{id}")
    public void deleteLesson(@PathVariable("id") int id) {
        System.out.println("deleting");
        lessonRepository.deleteById(id);
    }

    /**
     * findAllLessons
     * retrieves all the lessons
     * GET /api/lesson
     **/
    @CrossOrigin(origins = "*")
    @GetMapping("/api/lesson")
    public List<Lesson> findAllLessons() {
        return (List<Lesson>) lessonRepository.findAll();
    }

    /**
     * findLessonById
     * retrieves a lesson by id
     * GET /api/lesson/{id}
     **/
    @CrossOrigin(origins = "*")
    @GetMapping("/api/lesson/{id}")
    public Lesson findLessonById(@PathVariable("id") int id) {
        Optional<Lesson> c = lessonRepository.findById(id);
        if (c.isPresent()) {
            return c.get();
        } else {
            return null;
        }
    }

    /**
     findAllLessonsForCourse
     retrieves all lessons for course
     GET /api/course/{cid}/lesson
     **/
    @CrossOrigin(origins = "*")
    @GetMapping("/api/course/{cid}/module/{mid}/lesson")
    public List<Lesson> findAllLessonsForModule(@PathVariable("cid") int id, @PathVariable("mid") int mid) {
        Optional<Module> m  = moduleRepository.findById(mid);
        if (m.isPresent()){
            Module mod = m.get();

            return mod.getLessons();
        }
        return null;
    }

    /**
     * updateLesson
     * updates a lesson by id
     * PUT /api/lesson/{id}
     **/
    @CrossOrigin(origins = "*")
    @PutMapping("/api/lesson/{id}")
    public Lesson updateLesson(@PathVariable("id") int id, @RequestBody Lesson mod) {
        Optional<Lesson> m = lessonRepository.findById(id);
        if (m.isPresent()) {
            Lesson oldMod = m.get();
            //modify old course
            return lessonRepository.save(oldMod);
        }
        return null;
    }

}
