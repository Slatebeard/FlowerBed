package se.slatbeard.flowerbed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FlowerService {

    @Autowired
    private FlowerRepo flowerRepository;

    @Autowired
    private BouquetRepo bouquetRepository;

    // Flower Operations
    public void addFlower(String type, int quantity) {
        flowerRepository.save(new Flower(type, quantity));
    }


    // TODO CHORE ADD REMOVE BOOL!
    public void removeFlower(Long id) {
        flowerRepository.deleteById(id);
    }

    public Optional<Flower> getFlowerById(Long id) {
        return flowerRepository.findById(id);
    }

    public void updateFlower(Flower flower) {
        flowerRepository.save(flower);
    }

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    // Bouquet Operations
    public void addBouquet(int quantitySold, double price) {
        bouquetRepository.save(new Bouquet(quantitySold, price));
    }

    public List<Bouquet> getAllBouquets() {
        return bouquetRepository.findAll();
    }
}
