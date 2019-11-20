package ch.idsia.scenarios;

import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.LearningWithAS;

public final class Learn4_2 {
    public static void main(String[] args) {
        LearningAgent learningAgent = new LearningWithAS(
                "-lco off -lb on -le off -lhb off -lg on -ltb on -lhs off -lca on -lde on -ld 5 -ls 133829",
                "AS4_2"
        );
        learningAgent.learn();
        System.exit(0);
    }
}
