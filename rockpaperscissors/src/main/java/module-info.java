module com.fezekanzama {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.fezekanzama to javafx.fxml;
    exports com.fezekanzama;
}
