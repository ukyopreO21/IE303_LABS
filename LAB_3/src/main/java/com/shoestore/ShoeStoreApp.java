package com.shoestore;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class ShoeStoreApp extends Application {
    private final ShoeController controller = new ShoeController();

    private final ImageView mainImageView = new ImageView();
    private final Label mainBrandLabel = new Label();
    private final Label mainTitleLabel = new Label();
    private final Label mainPriceLabel = new Label();
    private final Label mainDescLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List<Product> products = controller.getSampleProducts();

        HBox root = new HBox(30);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #ffffff;");

        VBox leftPanel = createLeftPanel(products.get(0));
        ScrollPane rightPanel = createRightPanel(products);
        HBox.setHgrow(rightPanel, Priority.ALWAYS);

        root.getChildren().addAll(leftPanel, rightPanel);

        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setTitle("Shoe Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLeftPanel(Product initialProduct) {
        VBox leftBox = new VBox(10);
        leftBox.setPrefWidth(300);
        leftBox.setMinWidth(300);

        mainImageView.setFitWidth(300);
        mainImageView.setFitHeight(300);
        mainImageView.setPreserveRatio(true);

        Region divider = new Region();
        divider.setPrefHeight(1);
        divider.setStyle("-fx-background-color: #e0e0e0;");
        VBox.setMargin(divider, new Insets(10, 0, 10, 0));

        mainTitleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        mainTitleLabel.setTextFill(Color.web("#333333"));

        mainPriceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        mainPriceLabel.setTextFill(Color.web("#444444"));

        mainBrandLabel.setFont(Font.font("Arial", 14));
        mainBrandLabel.setTextFill(Color.web("#666666"));

        mainDescLabel.setFont(Font.font("Arial", 13));
        mainDescLabel.setTextFill(Color.web("#888888"));
        mainDescLabel.setWrapText(true);

        updateLeftPanelData(initialProduct);

        leftBox.getChildren().addAll(mainImageView, divider, mainTitleLabel, mainPriceLabel, mainBrandLabel, mainDescLabel);
        return leftBox;
    }

    private ScrollPane createRightPanel(List<Product> products) {
        FlowPane grid = new FlowPane(15, 15);
        grid.setPadding(new Insets(10));
        grid.setStyle("-fx-background-color: #ffffff;");

        for (Product product : products) {
            VBox card = createProductCard(product);
            grid.getChildren().add(card);
        }

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #ffffff; -fx-background-color: transparent;");
        scrollPane.setBorder(Border.EMPTY);
        return scrollPane;
    }

    private VBox createProductCard(Product product) {
        VBox card = new VBox(5);
        card.setPrefWidth(200);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 8; -fx-cursor: hand;");

        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: #e8e8e8; -fx-background-radius: 8; -fx-cursor: hand;"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 8; -fx-cursor: hand;"));

        Label title = new Label(product.getTitle());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label desc = new Label(product.getShortDesc());
        desc.setFont(Font.font("Arial", 12));
        desc.setTextFill(Color.web("#888888"));

        ImageView imgView = new ImageView();
        try {
            imgView.setImage(new Image(product.getImageUrl(), true));
        } catch (Exception ignored) {}

        imgView.setFitHeight(200);
        imgView.setPreserveRatio(true);

        VBox imageContainer = new VBox(imgView);
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setPrefHeight(200);

        HBox footer = new HBox();
        Label brand = new Label(product.getBrand());
        brand.setFont(Font.font("Arial", 12));
        brand.setTextFill(Color.web("#666666"));

        Label price = new Label(product.getPrice());
        price.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        footer.getChildren().addAll(brand, spacer, price);
        footer.setAlignment(Pos.BOTTOM_CENTER);

        card.getChildren().addAll(title, desc, imageContainer, footer);

        card.setOnMouseClicked(e -> controller.changeProductWithAnimation(product, mainImageView, mainTitleLabel, mainPriceLabel));

        return card;
    }

    private void updateLeftPanelData(Product p) {
        try {
            mainImageView.setImage(new Image(p.getImageUrl(), true));
        } catch (Exception ex) {
            System.out.println("Lỗi load ảnh: " + p.getImageUrl());
        }
        mainTitleLabel.setText(p.getTitle().toUpperCase());
        mainPriceLabel.setText(p.getPrice());
        mainBrandLabel.setText(p.getBrand());
        mainDescLabel.setText(p.getShortDesc());
    }
}