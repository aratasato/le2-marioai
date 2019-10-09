package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.GeneralizerLevelScene;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;
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
    }

    private boolean isObstacle(int r, int c) {
        return getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.BRICK
                || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.BORDER_CANNOT_PASS_THROUGH
                || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.FLOWER_POT_OR_CANNON
                || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.LADDER;
    }

    private boolean shouldAvoidGoomba() {
        int l = 10;
        int r = 15;
        for (int i = l; i <= r; i++) {
            if (getEnemiesCellValue(marioEgoRow, i) == Sprite.KIND_GOOMBA
                    || getEnemiesCellValue(marioEgoRow + 1, i) == Sprite.KIND_GOOMBA) {
                return true;
            }
        }
        return false;
    }

    public boolean[] getAction() {
        action[Mario.KEY_SPEED] = false;
        if (isObstacle(marioEgoRow, marioEgoCol + 1) || isObstacle(marioEgoRow, marioEgoCol + 2)) {
            action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
        }

        if (!isObstacle(marioEgoRow - 3, marioEgoCol + 7) && isObstacle(marioEgoRow, marioEgoCol + 8)) {
            action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
        }

        if (shouldAvoidGoomba()) {
            action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
        }

        if (isMarioAbleToShoot) {
            action[Mario.KEY_SPEED] = true;
        }

        return action;
    }
}