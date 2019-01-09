package com.zarzisdev.EShopApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {
	@Query(value="SELECT * FROM Term ORDER BY RAND() LIMIT 100", nativeQuery = true)
	public List<Term> findRandomTerms();
	
	@Query("select t from Term t where (t.fr like :x ) or (t.en like :x)")
	public List<Term> findByValue(@Param("x") String keyWord);
	
}
