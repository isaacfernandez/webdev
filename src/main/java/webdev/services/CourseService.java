package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Course;
import webdev.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    /**
     * createCourse
     * creates a course
     * POST /api/course
     **/
    @CrossOrigin(origins = "*")
    @PostMapping("/api/course")
    public Course createCourse(@RequestBody Course course) {
        Course c = courseRepository.save(course);
        return c;
    }

    /**
     * deleteCourse
     * deletes a course by id
     * DELETE /api/course/{id}
     **/
    @CrossOrigin(origins = "*")
    @DeleteMapping("api/course/{id}")
    public boolean deleteCourse(@PathVariable("id") int id) {
        try {
            courseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * findAllCourses
     * retrieves all the courses
     * GET /api/course
     **/
    @CrossOrigin(origins = "*")
    @GetMapping("/api/course")
    public List<Course> findAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    /**
     * findCourseById
     * retrieves a course by id
     * GET /api/course/{id}
     **/
    @CrossOrigin(origins = "*")
    @GetMapping("/api/course/{id}")
    public Course findCourseById(@PathVariable("id") int id) {
        Optional<Course> c = courseRepository.findById(id);
        if (c.isPresent()) {
            return c.get();
        } else {
            return null;
        }
    }

    /**
     * updateCourse
     * updates a course by id
     * PUT /api/course/{id}
     **/
    @CrossOrigin(origins = "*")
    @PutMapping("/api/course/{id}")
    public Course updateCourse(@PathVariable("id") int id, @RequestBody Course course) {
        Optional<Course> c = courseRepository.findById(id);
        if (c.isPresent()) {
            Course oldCourse = c.get();
            //modify old course
            return courseRepository.save(oldCourse);
        }
        return null;
    }
}