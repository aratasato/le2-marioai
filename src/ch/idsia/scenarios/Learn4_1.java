package ch.idsia.scenarios;

import ch.idsia.agents.Learn4;
import ch.idsia.agents.LearningAgent;

public final class Learn4_1 {
    public static void main(String[] args) {
        LearningAgent learningAgent = new Learn4(
                "-lde on -ltb off -ld 2 -ls 0 -le g",
                "4_1clear"
        );
        learningAgent.learn();
        System.exit(0);
    }
}
