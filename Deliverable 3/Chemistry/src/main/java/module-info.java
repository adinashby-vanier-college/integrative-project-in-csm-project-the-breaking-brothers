module com.example.chemistry {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.chemistry to javafx.fxml;
    exports com.example.chemistry;
}