package com.shoestore;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoeController {
    public List<Product> getSampleProducts() {
        List<Product> list = new ArrayList<>();
        String img1 = Objects.requireNonNull(getClass().getResource("/com/shoestore/images/img1.png")).toExternalForm();
        String img2 = Objects.requireNonNull(getClass().getResource("/com/shoestore/images/img2.png")).toExternalForm();
        String img3 = Objects.requireNonNull(getClass().getResource("/com/shoestore/images/img3.png")).toExternalForm();
        String img4 = Objects.requireNonNull(getClass().getResource("/com/shoestore/images/img4.png")).toExternalForm();
        String img5 = Objects.requireNonNull(getClass().getResource("/com/shoestore/images/img5.png")).toExternalForm();
        String img6 = Objects.requireNonNull(getClass().getResource("/com/shoestore/images/img6.png")).toExternalForm();
        list.add(new Product("4DFWD PULSE SHOES", "Adidas", "$160.00", "This product is excluded from all promotional discounts and offers", img1));
        list.add(new Product("FORUM MID SHOES", "Adidas", "$100.00", "This product is excluded from all promotional discounts and offers", img2));
        list.add(new Product("SUPERNOVA SHOES", "Adidas", "$150.00", "NMD City Stock 2", img3));
        list.add(new Product("Adidas", "Adidas", "$160.00", "NMD City Stock 2", img4));
        list.add(new Product("Adidas", "Adidas", "$120.00", "NMD City Stock 2", img5));
        list.add(new Product("4DFWD PULSE SHOES", "Adidas", "$160.00", "This product is excluded from all promotional discounts and offers", img6));

        return list;
    }

    public void changeProductWithAnimation(Product newProduct, ImageView mainImage, Label mainTitle, Label mainPrice) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), mainImage);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            try {
                mainImage.setImage(new Image(newProduct.getImageUrl(), true));
            } catch (Exception ignored) {}
            mainTitle.setText(newProduct.getTitle());
            mainPrice.setText(newProduct.getPrice());

            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), mainImage);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }
}
