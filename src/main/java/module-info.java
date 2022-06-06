module ru.homework_gb_6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.homework_gb_6 to javafx.fxml;
    exports ru.homework_gb_6;
}