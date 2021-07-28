package com.sdata;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.sdata.jpql_sql.model.Student;
import com.sdata.jpql_sql.repos.StudentRepo;




@SpringBootTest
public class StudentDataApplicationTests {
	@Autowired
	StudentRepo srepo;
	
	@Test
	public void testStudentCreate() {
		Student so = new Student();
		so.setFirstName("yogesh");
		so.setLastName("chauhan");
		so.setScore(77);
		srepo.save(so);
		
	}
	
	@Test
	public void testFindAllStudents() {
		System.out.println(srepo.findAllStudents(PageRequest.of(0, 2, Direction.DESC, "id")));
		
	}
	
	@Test
	public void testfindAllStudentPartialData() {
		List<Object[]> partial = srepo.findAllStudentsPartialData();
		for (Object[] objects : partial) {
			
			System.out.println(objects[0]);
			System.out.println(objects[1]);
		}
	}
		
	@Test
	public void testFindStudentByName() {
		System.out.println(srepo.findAllStudentByFirstName("yogi"));
	}
	
	@Test
	public void findAllStudentByScores() {
		System.out.println(srepo.findAllStudentByScores(75, 100));
	}
	
	@Test
	public void testDeleteStudentByName() {
		srepo.deleteStudentByFirstName("yogi");
	}
	
	
	@Test
	public void testAllStudentNativeQuery() {
		System.out.println(srepo.findByAllStudentNQ());
	}
	
	@Test
	public void testAllStudentByNameNativeQuery() {
		System.out.println(srepo.findByAllStudentByFristNameNQ("yk"));
	}
	
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	

}
