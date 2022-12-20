module com.mycompany.examendilorenlynch {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.examendilorenlynch to javafx.fxml;
    exports com.mycompany.examendilorenlynch;
}
