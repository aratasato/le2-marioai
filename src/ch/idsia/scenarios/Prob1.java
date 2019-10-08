package ch.idsia.scenarios;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.ForwardAgent;
import ch.idsia.agents.controllers.ForwardJumpingAgent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;

public final class Prob1 {
	public static void main(String[] args) {
		final MarioAIOptions marioAIOptions = new MarioAIOptions(args);

		int seed = 1;
		int d = 0;
		final Agent agent = new ForwardAgent();

	    marioAIOptions.setAgent(agent);
		marioAIOptions.setLevelRandSeed(seed);
		marioAIOptions.setLevelDifficulty(d);

		final BasicTask basicTask = new BasicTask(marioAIOptions);
		basicTask.setOptionsAndReset(marioAIOptions);
		basicTask.doEpisodes(1, true, 1);
		System.exit(0);
	}

}
