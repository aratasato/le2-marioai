package ch.idsia.scenarios;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.Prob2OwnAgent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;

public final class Prob2 {
    public static void main(String[] args) {
        final MarioAIOptions marioAIOptions = new MarioAIOptions(args);

        final Agent agent = new Prob2OwnAgent();
        marioAIOptions.setAgent(agent);

        int seed = 99;
        marioAIOptions.setLevelRandSeed(seed);

        final BasicTask basicTask = new BasicTask(marioAIOptions);
        basicTask.setOptionsAndReset(marioAIOptions);
        basicTask.doEpisodes(1, true, 1);
        System.exit(0);
    }
}
