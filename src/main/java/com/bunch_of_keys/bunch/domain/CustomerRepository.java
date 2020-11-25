package com.bunch_of_keys.bunch.domain;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

//    CustomerDao findBy
//public interface ExpensesRepository extends JpaRepository< Expenses, Long> {
//    @Query("FROM Expenses g where g.user.id = :userId")
//    GestaoGastos findAllByCurrentUser(@Param("userId") Long userId);
//}



//    List<Student> findByTeacher_TeacherId(String teacherId);
//your entityClass should be like..
//
//@Entity
//Class Student {
//  @Id
//  String studentId;
//  @ManyToOne
//  private Teacher teacher;
//}
//and Teacher Class would be..
//
//@Entity
//Class Teacher {
//  @Id
//  private String teacherId;
}