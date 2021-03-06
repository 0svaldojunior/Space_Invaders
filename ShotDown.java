import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
/**
 * Represents the shot down
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class ShotDown extends Shot
{
	private Image image;

    public ShotDown(int px, int py)
	{
        super(px, py);
		image = Params.getInstance().getTiroInimigo();
    }

    @Override
    public void start()
	{
        setDirV(1);
        setSpeed(2*Params.AumentaDificult);
		setLargAlt(5, 45);
    }

    @Override
	public void testaColisao(Character outro)
	{
		// Não verifica colisão de um tiro com outro tiro
		if (!(outro instanceof Shot) || (outro instanceof ShotDown))
		{
			return;
		}
		else
		{
			super.testaColisao(outro);
		}
	}

    @Override
    public void Update()
	{
        if (jaColidiu())
		{
            deactivate();
        }
		else
		{
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou na parte inferior da tela ...
			
			if (getY() >= getLMaxV())
			{
                // Desaparece
                deactivate();
            }
        }
    }

	@Override
	public void Draw(GraphicsContext graphicsContext)
	{
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
	}


}
