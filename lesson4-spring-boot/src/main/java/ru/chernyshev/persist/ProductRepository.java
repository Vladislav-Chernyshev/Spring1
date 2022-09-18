package ru.chernyshev.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """
            select * from products p
            where :productFilter is null or p.title like :productFilter
            """, nativeQuery = true)
    List<Product> productsByTitle(String productFilter);

    @Query(value = """
            select * from products p
            where :costFilter is null or p.cost = :costFilter
            """, nativeQuery = true)
    List<Product> productsByCost(Double costFilter);

    @Query(value = """
            select * from products p
            where p.cost >= :minCostFilter and p.cost <= :maxCostFilter
            order by p.cost desc
            """, nativeQuery = true)
    List<Product> productsSortMinAndMax(Double minCostFilter, Double maxCostFilter);

    @Query(value = """
            select * from products p
            where p.cost >= :costMinToMaxFilter
            order by p.cost asc
            """, nativeQuery = true)
    List<Product> minToMaxSort(Double costMinToMaxFilter);

    @Query(value = """
            select * from products p
            where p.cost <= :costMaxToMinFilter
            order by p.cost desc
            """, nativeQuery = true)
    List<Product> maxToMinSort(Double costMaxToMinFilter);
}
