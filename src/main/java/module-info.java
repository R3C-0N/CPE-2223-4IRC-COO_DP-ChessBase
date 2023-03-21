module irc4chessbase {
    /*
    exports shared;
    exports tools.introspection;
    exports controller;
    exports controller.localControler;
    exports launcher.withBuilder;
    exports view;
    exports tools.communication;
    exports model;
    exports model.strategy.movementStrategy;
    exports launcher;
    */

    exports launcher.localLauncher to javafx.graphics;
    exports launcher.withBuilder.local to javafx.graphics;
    exports model.noStrategy.pieces to org.apache.commons.lang3;
    
    
    // requires javafx.base;
    requires transitive javafx.controls;
    // requires javafx.graphics;
    requires org.apache.commons.lang3;
}
