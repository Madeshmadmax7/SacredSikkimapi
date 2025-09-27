package com.example.sacredsikkimapi.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonasteryDTO {

    private String id;
    private String title;
    private String subtitle;
    private String location;
    private String image;

    private CoordinatesDTO coordinates;
    private CapitalDTO capital;
    private AirportDTO airport;

    private List<NearbySpotDTO> nearbySpots;
    private List<String> description;
    private List<String> history;
    private List<HotspotDTO> hotspots;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CoordinatesDTO {
        private Double lat;
        private Double lng;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CapitalDTO {
        private Double lat;
        private Double lng;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AirportDTO {
        private Double lat;
        private Double lng;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class NearbySpotDTO {
        private String name;
        private Double lat;
        private Double lng;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HotspotDTO {
        private String position;
        private String title;
        private String image;
        private String description;
        private String descriptionhi;
    }
}