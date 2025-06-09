package com.ikea.product.service;

import com.ikea.product.model.Color;
import com.ikea.product.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Optional<Color> getColorById(String id) {
        return colorRepository.findById(id);
    }

    public Color addColor(Color color) {
        return colorRepository.save(color);
    }

    public void deleteAllColors() {
        colorRepository.deleteAll();
    }
}