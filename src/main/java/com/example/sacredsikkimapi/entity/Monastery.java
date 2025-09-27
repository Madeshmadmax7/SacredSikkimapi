package com.example.sacredsikkimapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "monasteries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monastery {

    @Id
    private String id;

    private String title;
    private String subtitle;
    private String location;
    private String image;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "coordinates_lat")),
            @AttributeOverride(name = "lng", column = @Column(name = "coordinates_lng"))
    })
    private Coordinates coordinates;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "capital_lat")),
            @AttributeOverride(name = "lng", column = @Column(name = "capital_lng")),
            @AttributeOverride(name = "name", column = @Column(name = "capital_name"))
    })
    private Capital capital;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "airport_lat")),
            @AttributeOverride(name = "lng", column = @Column(name = "airport_lng")),
            @AttributeOverride(name = "name", column = @Column(name = "airport_name"))
    })
    private Airport airport;

    @ElementCollection
    @CollectionTable(name = "nearby_spots", joinColumns = @JoinColumn(name = "monastery_id"))
    private List<NearbySpot> nearbySpots;

    @ElementCollection
    @CollectionTable(name = "description_lines", joinColumns = @JoinColumn(name = "monastery_id"))
    @Column(name = "description_line")
    private List<String> description;

    @ElementCollection
    @CollectionTable(name = "history_lines", joinColumns = @JoinColumn(name = "monastery_id"))
    @Column(name = "history_line")
    private List<String> history;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "monastery_id")
    private List<Hotspot> hotspots;

    // -------------------- EMBEDDABLES --------------------
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Coordinates {
        private Double lat;
        private Double lng;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Capital {
        private Double lat;
        private Double lng;
        private String name;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Airport {
        private Double lat;
        private Double lng;
        private String name;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class NearbySpot {
        private String name;
        private Double lat;
        private Double lng;
    }

    @Entity
    @Table(name = "hotspots")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Hotspot {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String position;
        private String title;
        private String image;
        private String description;
        @Column(name = "description_hi", length = 2000)
        private String descriptionhi;
    }
}