package com.sdata.jpql_sql.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sdata.jpql_sql.model.Student;



public interface StudentRepo extends CrudRepository<Student, Long> {

   
   
   @Query("from Student")
	List<Student> findAllStudents(Pageable pageable);
   
   @Query("select st.firstName,st.lastName from Student st")
	List<Object[]> findAllStudentsPartialData();
	
	@Query("from Student where firstName=:firstName")
	List<Student> findAllStudentByFirstName(@Param("firstName") String firstName);
	
	@Query("From Student where score>:min and score<:max")
	List<Student> findAllStudentByScores(@Param("min") int min, @Param("max") int max);
	
	@Transactional
	@Modifying
	@Query("delete from Student where firstName = :firstName")
	void deleteStudentByFirstName(@Param("firstName") String firstName);
	
	@Query(value = "select * from student", nativeQuery = true)
	List<Student> findByAllStudentNQ();
	
	@Query(value = "select * from student where fname=:firstName", nativeQuery = true)
	List<Student> findByAllStudentByFristNameNQ(@Param("firstName") String firstName);
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
