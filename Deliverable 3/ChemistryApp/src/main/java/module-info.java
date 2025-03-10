module com.example.chemistryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.auth;

    requires org.kordamp.bootstrapfx.core;
    requires firebase.admin;
    requires com.google.auth.oauth2;

    opens com.example.chemistryapp to javafx.fxml;
    opens com.example.chemistryapp.Controller to javafx.fxml;
    exports com.example.chemistryapp.Controller to javafx.fxml;
    exports com.example.chemistryapp;

}
