package se.slatbeard.flowerbed;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FlowerRepo extends JpaRepository<Flower, Long> {
}
