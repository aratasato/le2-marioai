package ch.idsia.scenarios;

import ch.idsia.agents.Agent;
import ch.idsia.agents.AgentsPool;
import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.LearningWithAS;
import ch.idsia.agents.controllers.ForwardAgent;
import ch.idsia.agents.controllers.ForwardJumpingAgent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;

public final class Prob1 {
    public static void main(String[] args) {
//        Agent agent = new ForwardAgent();

        AgentsPool.addAgent(AgentsPool.loadAgent("AS1_256.xml", false));
        final Agent agent = AgentsPool.getCurrentAgent();
        String aiOptionsArgs = "-ld 0 -ls 1";

        // learn
//        learnWithAS(aiOptionsArgs, "AS1");

        // play
        play(aiOptionsArgs, agent);
    }

    private static void play(String args, Agent agent) {
        final MarioAIOptions marioAIOptions = new MarioAIOptions();

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
