package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.GeneralizerLevelScene;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public class Prob3OwnAgent extends BasicMarioAIAgent implements Agent {
    int trueJumpCounter = 0;
    int trueSpeedCounter = 0;

    public Prob3OwnAgent() {
        super("OwnAgent");
        reset();
    }

    public void reset() {
        action = new boolean[Environment.numberOfKeys];
        action[Mario.KEY_RIGHT] = true;
//        action[Mario.KEY_SPEED] = true;
    }

    private boolean isObstacle(int r, int c) {
        return getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.BRICK
                || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.BORDER_CANNOT_PASS_THROUGH
                || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.FLOWER_POT_OR_CANNON
                || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.LADDER;
    }

    public boolean[] getAction() {
        if (isObstacle(marioEgoRow, marioEgoCol + 1)
                || isObstacle(marioEgoRow, marioEgoCol + 2)
                || isObstacle(marioEgoRow, marioEgoCol)) {
            action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
        }

        if (!isObstacle(marioEgoRow - 3, marioEgoCol + 7) && isObstacle(marioEgoRow, marioEgoCol + 8)) {
            action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
        }

        if (!isMarioOnGround) {
            action[Mario.KEY_SPEED] = true;
        }

        return action;
    }
}