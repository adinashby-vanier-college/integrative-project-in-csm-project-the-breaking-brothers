module com.example.chemistryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.auth;
    requires javafx.graphics;

    requires javafx.web;
    requires java.desktop;

    requires org.kordamp.bootstrapfx.core;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires grpc.grpclb;
    requires org.jsoup;

    opens com.example.chemistryapp to javafx.fxml;
    opens com.example.chemistryapp.Controller to javafx.fxml;
    exports com.example.chemistryapp.Controller to javafx.fxml;
    exports com.example.chemistryapp;
    exports com.example.chemistryapp.View to javafx.graphics;

}
