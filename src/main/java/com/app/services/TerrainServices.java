package com.app.services;

import com.app.entities.Terrain;
import com.app.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TerrainServices {
    private final TerrainRepository terrainRepository;

    public List<Terrain> getAllTerrains() {
        return terrainRepository.findAll();
    }

    public Page<Terrain> getAllTerrains(Pageable p) {
        return terrainRepository.findAll(p);
    }

    public Optional<Terrain> getTerrain(String id) {
        return terrainRepository.findById(id);
    }

    public Optional<Terrain> getTerrainByCode(String code) {
        return terrainRepository.findByCode(code);
    }

    public Terrain save(Terrain terrain) {
        if(terrainRepository.findByCode(terrain.getCode()).isPresent() && !Objects.equals(terrainRepository.findByCode(terrain.getCode()).get().getId(), terrain.getId())) {
            throw new IllegalStateException("Terrain with code "+terrain.getCode()+" already saved");
        }
        return terrainRepository.save(terrain);
    }
    public Terrain delete(String id) {
        if(!terrainRepository.findById(id).isPresent()) {
            throw new IllegalStateException("No Terrain Exist with id "+id);
        }
        Terrain terrain = terrainRepository.findById(id).get();
        terrainRepository.delete(terrain);
        return terrain;
    }
}
