module com.example.graph {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.graph to javafx.fxml;
    exports com.example.graph;
}