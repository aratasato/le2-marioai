package ch.idsia.scenarios;

import ch.idsia.agents.Agent;
import ch.idsia.agents.AgentsPool;
import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.LearningWithAS;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;

public final class ASTest {
    public static void main(String[] args) {
        String aiOptionsArgs = "-ld 1 -ls 1 -jp 3";

        // learn
        learnWithAS(aiOptionsArgs, "ASTest");

        // play
//        AgentsPool.addAgent(AgentsPool.loadAgent("ASTest_256.xml", false));
//        final Agent agent = AgentsPool.getCurrentAgent();
//        play(aiOptionsArgs, agent);
    }

    private static void play(String args, Agent agent) {
        MarioAIOptions marioAIOptions = new MarioAIOptions();

        marioAIOptions.setAgent(agent);
        marioAIOptions.setArgs(args);

        final BasicTask basicTask = new BasicTask(marioAIOptions);
        basicTask.setOptionsAndReset(marioAIOptions);
        basicTask.doEpisodes(1, true, 1);
        System.exit(0);
    }

    private static void learnWithAS(String args, String filename) {
        LearningAgent learningAgent = new LearningWithAS(
                args,
                filename
        );
        learningAgent.learn();
        System.exit(0);
    }
}
