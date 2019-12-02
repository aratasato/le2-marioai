package ch.idsia.scenarios;

import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.Learn4;

public final class Learn4_3 {
    public static void main(String[] args) {
        LearningAgent learningAgent = new Learn4(
                "-lde on -i off -ld 30 -ls 133434 -lhb on",
                "4-3clear"
        );
        learningAgent.learn();
    }
}
