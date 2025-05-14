module mokki.mokki {
    requires javafx.controls;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;


    exports mokki.mokki;
    exports mokki.mokki.database;
    exports mokki.mokki.gui.TaulukkoWrapper;
    exports mokki.mokki.gui.alipaneeli;
    exports mokki.mokki.gui.paapaneeli;
    exports mokki.mokki.gui;

}