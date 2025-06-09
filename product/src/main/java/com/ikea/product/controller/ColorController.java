package com.ikea.product.controller;

import com.ikea.product.model.Color;
import com.ikea.product.service.ColorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@Tag(name = "Color API", description = "Operations for managing colors")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @Operation(summary = "Find all colors", description = "Retrieves a list of all available colors")
    @GetMapping
    public ResponseEntity<List<Color>> findAll() {
        List<Color> colors = colorService.getAllColors();
        return ResponseEntity.ok(colors);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Find color by ID", description = "Retrieves a color by its unique identifier")
    public ResponseEntity<Color> findById(@PathVariable String id) {
        return colorService.getColorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add a new color
    @PostMapping
    public ResponseEntity<Color> addColor(@RequestBody Color color) {
        Color savedColor = colorService.addColor(color);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedColor);
    }
}