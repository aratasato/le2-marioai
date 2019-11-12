package ch.idsia.scenarios;

import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.LearningWithAStar;
import ch.idsia.agents.LearningWithGA;
import ch.idsia.tools.MarioAIOptions;

public class Learn4_1 {
    public static void main(String[] args) {
        LearningAgent learningAgent = new LearningWithAStar("-lde on -i on -ltb off -ld 2 -ls 0 -le g");
        System.out.println("main.learningAgent = " + learningAgent);
        System.exit(0);
    }
}
