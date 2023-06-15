module com.foofinc.updatecleaner {
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    exports com.foofinc.updatecleaner;
    exports com.foofinc.updatecleaner.properties to com.fasterxml.jackson.databind;
}