package ch.idsia.agents;

import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.benchmark.tasks.LearningTask;
import ch.idsia.tools.EvaluationInfo;
import ch.idsia.tools.MarioAIOptions;
import ch.idsia.utils.wox.serial.Easy;

import java.util.HashMap;

public class LearningWithAS implements LearningAgent {

    private ASAgent agent;
    private ASAgent bestAgent;
    private String args;
    // actionsのインデックス
    private static int actionIndex = 0;
    // ダメージを受けた時のインデックス
    private static int damagedIndex = 0;
    // 死んだ地点
    private static int deathPoint = 0;
    // 死んだ地点を記録するハッシュマップ
    private HashMap<Integer, Integer> deathPoints = new HashMap<Integer, Integer>();

    private int bestDistance = 0;


    /* LearningWithAStarのコンストラクタ */
    public LearningWithAS(String args) {
        agent = new ASAgent();
        bestAgent = agent.clone();
        this.args = args;
    }

    // 学習
    public void learn() {
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        BasicTask basicTask = new BasicTask(marioAIOptions);
        marioAIOptions.setArgs(args);
        marioAIOptions.setVisualization(false);

        /* クリアするまで */
        int i = 0;
        while (true) {
            // init
            marioAIOptions.setAgent(agent);
            basicTask.setOptionsAndReset(marioAIOptions);
            damagedIndex = 0;
            actionIndex = 0;
            deathPoint = 0;

            // 1万回に1回プレイ画面を表示
            if (i % 1000 == 0 && i > 0) {
                marioAIOptions.setVisualization(true);
                marioAIOptions.setFPS(10000);
            } else {
                marioAIOptions.setVisualization(false);
            }

            // play
            if (!basicTask.runSingleEpisode(1)) {
                System.out.println("MarioAI: out of computational time per action! Agent disqualified!");
            }

            // evaluation
            EvaluationInfo evaluationInfo = basicTask.getEvaluationInfo();
            if (i % 100 == 0) {
                System.out.println(String.format("%d: %d", i, bestDistance));
            }

            // 死んだ地点を取得し記録する
            deathPoint = evaluationInfo.distancePassedCells;
            if (bestDistance < deathPoint) {
                bestDistance = deathPoint;
                deathPoints = new HashMap<Integer, Integer>();
                System.out.println(bestDistance);
            }
            if (deathPoints.containsKey(deathPoint)) {
                deathPoints.put(deathPoint, deathPoints.get(deathPoint) + 1);
            } else {
                deathPoints.put(deathPoint, 1);
            }

            damagedIndex = agent.getDamagedIndex();
            actionIndex = agent.getActionIndex();

            // クリアしたらループを抜ける
            if (evaluationInfo.distancePassedCells >= 256 && evaluationInfo.marioMode == 2) {
                byte[] actions = agent.getActions();
                ASAgent clearAgent = new ASAgent();
                clearAgent.setActions(actions);
                writeFile(clearAgent, "AS4-3", evaluationInfo.distancePassedCells);
                break;
            }


            // 次の世代のagentを用意
            ASAgent nextAgent = getNextAgent(agent);

            // agentにコピー
            agent = nextAgent.clone();

            i++;
        }

        // 確認のためbestAgentを実行
        marioAIOptions.setAgent(bestAgent);
        marioAIOptions.setVisualization(true);
        basicTask.setOptionsAndReset(marioAIOptions);
        if (!basicTask.runSingleEpisode(1)) {
            System.out.println("MarioAI: out of computational time"
                    + " per action! Agent disqualified!");
        }

    }

    /* 次のactionsを決定する
     * actionIndex：死んだ時のindex
     * damagedIndex：ダメージを受けた時のindex
     * deathPoint：死んだ地点
     */
    private ASAgent getNextAgent(ASAgent agent) {
        ASAgent nextAgent = new ASAgent();
        byte[] actions = agent.getActions();

        // 巻き戻すインデックス
        int rewindIndex = Math.max(deathPoints.get(deathPoint) / 15, 0);

        // ダメージを受けた時
        if (damagedIndex < actionIndex && damagedIndex != 0) {
            for (int i = damagedIndex - 10; i < actions.length; i++) {
                actions[i] = 0;
            }
        }
        // 行き詰まった時
        if (agent.getIsDeadEnd()) {
            for (int i = agent.getDeadEndIndex() - 30; i < actions.length; i++) {
                actions[i] = 0;
            }
        }
        // 同じ場所で死に過ぎたらカウントを初期化
        if (rewindIndex > actionIndex) {
            deathPoints.put(deathPoint, 1);
            // 同じ場所で何回も死んだ時
        } else if (rewindIndex >= 20) {
            for (int i = actionIndex - rewindIndex; i < actions.length; i++) {
                actions[i] = 0;
            }
        }
        // 普通に死んだ時
        for (int i = actionIndex - 10; i < actions.length; i++) {
            actions[i] = 0;
        }
        // actionsをコピー
        nextAgent.setActions(actions);

        return nextAgent;
    }

    /* 書き出す */
    private void writeFile(Agent agent, String name, int value) {
        String fileName = String.format("%s_%d.xml", name, value);
        Easy.save(agent, fileName);
    }

    @Override
    public void giveReward(float reward) {

    }

    @Override
    public void newEpisode() {

    }

    @Override
    public void setLearningTask(LearningTask learningTask) {

    }

    @Override
    public void setEvaluationQuota(long num) {

    }

    @Override
    public Agent getBestAgent() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean[] getAction() {
        return new boolean[0];
    }

    @Override
    public void integrateObservation(Environment environment) {

    }

    @Override
    public void giveIntermediateReward(float intermediateReward) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void setObservationDetails(int rfWidth, int rfHeight, int egoRow, int egoCol) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }
}