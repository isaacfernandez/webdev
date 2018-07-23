package webdev.services;

import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

public class LessonService {
    LessonRepository lessonRepository;
    ModuleRepository moduleRepository;

    /**
     * createLesson
     * creates a lesson for a course
     * POST /api/course/{cid}/lesson
     **/
    @PostMapping("/api/course/{cid}/module/{mid}/lesson")
    public Lesson createLesson(@PathVariable(name="mid") int mid) {
        Optional<Module> optionalModule = moduleRepository.findById(mid);
        // ...
        return null;
    }

    /**
     * deleteLesson
     * deletes a lesson by id
     * DELETE /api/lesson/{id}
     **/
    @DeleteMapping("/api/lesson/{id}")
    public void deleteLesson(@PathVariable("id") int id) {
        lessonRepository.deleteById(id);
    }

    /**
     * findAllLessons
     * retrieves all the lessons
     * GET /api/lesson
     **/
    @GetMapping("/api/lesson")
    public List<Lesson> findAllLessons() {
        return (List<Lesson>) lessonRepository.findAll();
    }

    /**
     * findLessonById
     * retrieves a lesson by id
     * GET /api/lesson/{id}
     **/
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

    /**
     * updateLesson
     * updates a lesson by id
     * PUT /api/lesson/{id}
     **/
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
