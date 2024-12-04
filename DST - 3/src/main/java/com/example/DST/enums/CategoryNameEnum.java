package com.example.DST.enums;

import lombok.Getter;

@Getter
public enum CategoryNameEnum {
    HOTEL("https://firebasestorage.googleapis.com/v0/b/codedst-3f16c.appspot.com/o/PictureCategory%2FHotel.jpg?alt=media"),
    CAR_RENTAL("https://firebasestorage.googleapis.com/v0/b/codedst-3f16c.appspot.com/o/PictureCategory%2FCar_rental.jpg?alt=media"),
    FOOD("https://firebasestorage.googleapis.com/v0/b/codedst-3f16c.appspot.com/o/PictureCategory%2FFood.jpg?alt=media"),
    HOT_SPRING("https://firebasestorage.googleapis.com/v0/b/codedst-3f16c.appspot.com/o/PictureCategory%2FHot_spring.jpg?alt=media"),
    SOUVENIR("https://firebasestorage.googleapis.com/v0/b/codedst-3f16c.appspot.com/o/PictureCategory%2FSouvenir.jpg?alt=media");

    // Phương thức để lấy giá trị pictureUrl
    private final String pictureUrl;

    // Constructor của enum để khởi tạo giá trị pictureUrl cho từng category
    CategoryNameEnum(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}