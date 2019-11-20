package ch.idsia.scenarios;

import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.LearningWithAS;

public final class Learn4_3 {
    public static void main(String[] args) {
        LearningAgent learningAgent = new LearningWithAS(
                "-lde on -i off -ld 30 -ls 133434 -lhb on",
                "AS4-3"
        );
        learningAgent.learn();
    }
}
