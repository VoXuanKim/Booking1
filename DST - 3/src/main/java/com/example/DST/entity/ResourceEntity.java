package com.example.DST.entity;


import com.example.DST.enums.ResourceStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resources")
public class ResourceEntity extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resources_id")
    private int resourcesId;
    private String resourcesName;
    private String resourcesDescription;
    private String resourcesPrice;
    private String resourcesCapacity;
    private String resourcesPhoto;
    @Enumerated(EnumType.STRING)
    private ResourceStatusEnum resourceStatusEnum;

    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
    private List<BookingEntity> bookings;

    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;
}
