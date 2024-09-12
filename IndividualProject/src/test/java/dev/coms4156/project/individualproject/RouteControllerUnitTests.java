package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
public class RouteControllerUnitTests {

  private RouteController routeController;

  @BeforeEach
  public void setup() {
    routeController = new RouteController();
  }

  public void setupMocks() {
    HashMap<String, Department> departmentMapping = new HashMap<>();
    HashMap<String, Course> courses = new HashMap<>();
    Course course = new Course("John Doe", "101 Building", "9:00-10:00", 100);
    courses.put("4156", course);
    
    Department department = new Department("TEST", courses, "Jane Smith", 1500);
    departmentMapping.put("COMS", department);
  }

  @Test
  public void indexTest() {
    String expectedResult = "Welcome, in order to make an API call direct your browser or "
        + "Postman to an endpoint \n\n"
        + "This can be done using the following format: \n\n http:127.0.0"
        + ".1:8080/endpoint?arg=value";
    assertEquals(expectedResult, routeController.index());
  }

  @Test
  public void retrieveDepartmentTest() {
    ResponseEntity<?> response = routeController.retrieveDepartment("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Department details returned successfully", response.getBody());

    response = routeController.retrieveDepartment("RANDOM");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void retrieveCourseTest() {
    ResponseEntity<?> response = routeController.retrieveCourse("ECON", 1105);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Course details returned successfully", response.getBody());

    response = routeController.retrieveCourse("ECON", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());

    response = routeController.retrieveCourse("RANDOM", 1105);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void isCourseFullTest() {
    ResponseEntity<?> response = routeController.isCourseFull("COMS", 4156);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("false", response.getBody());
    
    response = routeController.isCourseFull("COMS", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void getMajorCtFromDeptTest() {
    ResponseEntity<?> response = routeController.getMajorCtFromDept("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("There are: 2700 majors in the department", response.getBody());

    response = routeController.getMajorCtFromDept("RANDOM");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void identifyDeptChairTest() {
    ResponseEntity<?> response = routeController.identifyDeptChair("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Luca Carloni is the department chair.", response.getBody());

    response = routeController.identifyDeptChair("RANDOM");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void findCourseLocationTest() {
    ResponseEntity<?> response = routeController.findCourseLocation("COMS", 4156);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("501 NWC is where the course is located.", response.getBody());

    response = routeController.findCourseLocation("COMS", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());

    response = routeController.findCourseLocation("RANDOM", 4156);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void findCourseInstructorTest() {
    ResponseEntity<?> response = routeController.findCourseInstructor("COMS", 4156);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Gail Kaiser is the instructor for this course.", response.getBody());

    response = routeController.findCourseInstructor("COMS", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());

    response = routeController.findCourseInstructor("RANDOM", 4156);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void findCourseTimeTest() {
    ResponseEntity<?> response = routeController.findCourseTime("COMS", 4156);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("The course meets at: 10:10-11:25.", response.getBody());

    response = routeController.findCourseTime("COMS", 1);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());

    response = routeController.findCourseTime("RANDOM", 4156);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void addMajorToDeptTest() {
    ResponseEntity<?> response = routeController.addMajorToDept("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attribute was updated successfully", response.getBody());

    response = routeController.addMajorToDept("RANDOM");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void removeMajorFromDeptTest() {
    ResponseEntity<?> response = routeController.removeMajorFromDept("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attribute was updated or is at minimum", response.getBody());

    response = routeController.removeMajorFromDept("RANDOM");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void dropStudentFromCourseTest() {
    ResponseEntity<?> response = routeController.dropStudent("COMS", 4156);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Student has been dropped.", response.getBody());

    response = routeController.dropStudent("RANDOM", 4156);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void setEnrollmentCountTest() {
    ResponseEntity<?> response = routeController.setEnrollmentCount("COMS", 4156, 300);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());

    response = routeController.setEnrollmentCount("RANDOM", 4156, 300);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void changeCourseTimeTest() {
    // Test successful course instructor change
    ResponseEntity<?> response = routeController.changeCourseTime("COMS", 4156, "4:10-5:25");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());

    // Test when course doesn't exist in the department
    response = routeController.changeCourseTime("COMS", 1, "10:10-11:25");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());

    // Test when department doesn't exist
    response = routeController.changeCourseTime("RANDOM", 4156, "10:10-11:25");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void changeCourseTeacherTest() {
    // Test successful course instructor change
    ResponseEntity<?> response = routeController.changeCourseTeacher(
        "COMS", 
        4156, 
        "Nicole Lin"
    );
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());

    // Test when course doesn't exist in the department
    response = routeController.changeCourseTeacher("COMS", 1, "Gail Kaiser");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());

    // Test when department doesn't exist
    response = routeController.changeCourseTeacher("RANDOM", 4156, "Gail Kaiser");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void changeCourseLocationTest() {
    // Test successful course location change
    ResponseEntity<?> response = routeController.changeCourseLocation("COMS", 4156, "501 SCH");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());

    // Test when course doesn't exist in the department
    response = routeController.changeCourseLocation("COMS", 1, "501 SCH");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());

    // Test when department doesn't exist
    response = routeController.changeCourseLocation("RANDOM", 4156, "501 SCH");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

    
}
