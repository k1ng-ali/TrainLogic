package org.example.trainlogic;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.Node;

public class ShakeAnimation {
    private TranslateTransition tt;

    public ShakeAnimation(Node node) {
        tt = new TranslateTransition(Duration.millis(70), node);
        node.setStyle("-fx-border-color: #804949");
        node.setStyle("-fx-background-color: rgba(128,73,73,0.18)");
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);
    }

    public void playAnimation() {
        tt.playFromStart();
    }
}
