package com.example.java5n_sd19303.repository;

import com.example.java5n_sd19303.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepositoryV2 extends JpaRepository<Student, Long> {

    // 1. Built-in methods: save, findById, ...
    // JpaRepository / PagingAndSortingRepository, CrudRepository

    // 2. Methods: ghep ten
    // find...By, read...By, get...By, query...By
    // First, Top: FindFirst2ByName, findTop10ByName
    // Distinct: findDistinctByName
    // AND, OR: findByNameOrDescription, findByNameAndPrice
    // example like/containing
    List<Student> findByNameContaining(String name);

    // 3. JPQL/Native query with named parameters
    // using JPQL + index params
    @Query("SELECT s FROM Student s WHERE s.name=?1 OR s.id=?2")
    List<Student> findByNameOrIdV3a(String name, long id);

    // using native query + named params
    @Query(value = "SELECT * FROM students s WHERE s.name=:name OR s.id=:id", nativeQuery = true)
    List<Student> findByNameOrIdV3b(String name, long id);


    // 4. Named query


}
