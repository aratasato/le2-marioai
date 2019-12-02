package ch.idsia.scenarios;

import ch.idsia.agents.Agent;
import ch.idsia.agents.AgentsPool;
import ch.idsia.agents.LearningAgent;
import ch.idsia.agents.Learn4;
import ch.idsia.agents.controllers.ForwardAgent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;

public final class Prob1 {
    public static void main(String[] args) {
        final Agent agent = new ForwardAgent();
        String aiOptionsArgs = "-ld 0 -ls 1";

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
}
