import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
/**
 * Represents the shot boss
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class ShotBoss extends ShotDown
{

	public ShotBoss(int px, int py)
	{
        super(px, py);
    }

    @Override
    public void start()
	{
        setDirV(1);
        setSpeed(8*Params.AumentaDificult);
		setLargAlt(20,65);
    }

}
