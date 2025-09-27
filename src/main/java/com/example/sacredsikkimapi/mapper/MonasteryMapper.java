package com.example.sacredsikkimapi.mapper;

import com.example.sacredsikkimapi.dto.MonasteryDTO;
import com.example.sacredsikkimapi.entity.Monastery;
import org.springframework.stereotype.Component;

import java.util.ArrayList; // Needed for the fix
import java.util.stream.Collectors;

@Component
public class MonasteryMapper {

        public MonasteryDTO toDTO(Monastery entity) {
                if (entity == null)
                        return null;

                return MonasteryDTO.builder()
                                .id(entity.getId())
                                .title(entity.getTitle())
                                .subtitle(entity.getSubtitle())
                                .location(entity.getLocation())
                                .image(entity.getImage())
                                .coordinates(toCoordinatesDTO(entity.getCoordinates()))
                                .capital(toCapitalDTO(entity.getCapital()))
                                .airport(toAirportDTO(entity.getAirport()))
                                .nearbySpots(entity.getNearbySpots() == null ? null
                                                : entity.getNearbySpots().stream().map(this::toNearbySpotDTO)
                                                                .collect(Collectors.toList()))
                                .description(entity.getDescription())
                                .history(entity.getHistory())
                                .hotspots(entity.getHotspots() == null ? null
                                                : entity.getHotspots().stream().map(this::toHotspotDTO)
                                                                .collect(Collectors.toList()))
                                .build();
        }

        public Monastery toEntity(MonasteryDTO dto) {
                if (dto == null)
                        return null;

                Monastery entity = Monastery.builder()
                                .id(dto.getId())
                                .build();

                updateEntityFromDTO(dto, entity);
                return entity;
        }

        // ----------------- New method: updates existing entity -----------------
        public void updateEntityFromDTO(MonasteryDTO dto, Monastery entity) {
                if (dto == null || entity == null)
                        return;

                entity.setTitle(dto.getTitle());
                entity.setSubtitle(dto.getSubtitle());
                entity.setLocation(dto.getLocation());
                entity.setImage(dto.getImage());

                entity.setCoordinates(dto.getCoordinates() == null ? null
                                : Monastery.Coordinates.builder()
                                                .lat(dto.getCoordinates().getLat())
                                                .lng(dto.getCoordinates().getLng())
                                                .build());

                entity.setCapital(dto.getCapital() == null ? null
                                : Monastery.Capital.builder()
                                                .lat(dto.getCapital().getLat())
                                                .lng(dto.getCapital().getLng())
                                                .name(dto.getCapital().getName())
                                                .build());

                entity.setAirport(dto.getAirport() == null ? null
                                : Monastery.Airport.builder()
                                                .lat(dto.getAirport().getLat())
                                                .lng(dto.getAirport().getLng())
                                                .name(dto.getAirport().getName())
                                                .build());

                entity.setNearbySpots(dto.getNearbySpots() == null ? null
                                : dto.getNearbySpots().stream()
                                                .map(ns -> Monastery.NearbySpot.builder()
                                                                .name(ns.getName())
                                                                .lat(ns.getLat())
                                                                .lng(ns.getLng())
                                                                .build())
                                                .collect(Collectors.toList()));

                entity.setDescription(dto.getDescription());
                entity.setHistory(dto.getHistory());

                // CRITICAL FIX: Initialize the collection if it is null
                if (entity.getHotspots() == null) {
                        entity.setHotspots(new ArrayList<>());
                }

                // Now it's safe to clear the list
                entity.getHotspots().clear();

                if (dto.getHotspots() != null) {
                        entity.getHotspots().addAll(dto.getHotspots().stream()
                                        .map(h -> Monastery.Hotspot.builder()
                                                        .position(h.getPosition())
                                                        .title(h.getTitle())
                                                        .image(h.getImage())
                                                        .description(h.getDescription())
                                                        .descriptionhi(h.getDescriptionhi())
                                                        .build())
                                        .collect(Collectors.toList()));
                }
        }

        // ---------------- DTO helpers ----------------
        private MonasteryDTO.CoordinatesDTO toCoordinatesDTO(Monastery.Coordinates coordinates) {
                if (coordinates == null)
                        return null;
                return new MonasteryDTO.CoordinatesDTO(coordinates.getLat(), coordinates.getLng());
        }

        private MonasteryDTO.CapitalDTO toCapitalDTO(Monastery.Capital capital) {
                if (capital == null)
                        return null;
                return new MonasteryDTO.CapitalDTO(capital.getLat(), capital.getLng(), capital.getName());
        }

        private MonasteryDTO.AirportDTO toAirportDTO(Monastery.Airport airport) {
                if (airport == null)
                        return null;
                return new MonasteryDTO.AirportDTO(airport.getLat(), airport.getLng(), airport.getName());
        }

        private MonasteryDTO.NearbySpotDTO toNearbySpotDTO(Monastery.NearbySpot spot) {
                return new MonasteryDTO.NearbySpotDTO(spot.getName(), spot.getLat(), spot.getLng());
        }

        private MonasteryDTO.HotspotDTO toHotspotDTO(Monastery.Hotspot hotspot) {
                return new MonasteryDTO.HotspotDTO(
                                hotspot.getPosition(),
                                hotspot.getTitle(),
                                hotspot.getImage(),
                                hotspot.getDescription(),
                                hotspot.getDescriptionhi());
        }
}