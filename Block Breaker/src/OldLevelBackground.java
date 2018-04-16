
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author orel
 */
public class OldLevelBackground implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }



    @Override
    public Sprite getBackground() {
        LineSprite lineSp;
        LevelBackground bg = new LevelBackground();
        RectangleSprite rect = new RectangleSprite(new Rectangle(new Point(30, 30), 740, 570),
                new Color(135, 206, 250), true);
        bg.addSprite(rect);
        Point end;
        Point start = new Point(400, 200);
        for (int i = 0; i <= 20; i++) {
            end = new Point(i * 38, 591);
            lineSp = new LineSprite(new Line(start, end),
                    new Color(240, 231, 135));
            bg.addSprite(lineSp);
        }

        CircleSprite circle = new CircleSprite(new Point(401, 201), 151,
                new Color(254, 235, 138), true);
        bg.addSprite(circle);
        circle = new CircleSprite(new Point(400, 200), 111,
                new Color(254, 245, 144), true);
        bg.addSprite(circle);
        circle = new CircleSprite(new Point(400, 200), 71,
                new Color(253, 249, 204), true);
        bg.addSprite(circle);

        return bg;
    }



    @Override
    public List<BlockInterface> blocks() {
        List<BlockInterface> wideEasyBlocks = new ArrayList<BlockInterface>();
        for (int i = 0; i < 1; i++) {
            for (int j = i; j < 15; j++) {
                Block block = new Block(new Rectangle(
                        new Point((j * 49) + 32, (i * 30) + 250), 49, 30));
                wideEasyBlocks.add(block);
            }
        }
        return wideEasyBlocks;
    }


    @Override
    public int paddleSpeed() {
        return 150;
    }

    @Override
    public String levelName() {
        return new String("Wide Easy");
    }


    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int addative = 0;
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < 5; i++, addative += 5) {
            velocities
                    .add(Velocity.fromAngleAndSpeed(330 + addative, 100));
        }
        for (int j = 4; j < 10; j++) {
            velocities
                    .add(Velocity.fromAngleAndSpeed(30 + addative, -100));
        }
        return velocities;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

}