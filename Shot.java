import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * Represents the shot
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Shot extends BasicElement
{
	private Image image;
	public static boolean atirado, bateu;
	
    public Shot(int px,int py)
	{
        super(px,py);
		image = Params.getInstance().getTiroNave();
    }
    
    @Override
    public void start()
	{
        setDirV(-1);
        setSpeed(50);
        setLargAlt(5, 45);
    }
            
    @Override
    public void testaColisao(Character outro)
	{
        // Não verifica colisão de um tiro com outro tiro
        if (outro instanceof Shot)
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
			atirado = false;
			bateu = true;
			deactivate();
		}
		else
		{
			setPosY(getY() + getDirV() * getSpeed());
			// Se chegou na parte superior da tela ...
			if (getY() <= getLMinV())
			{
				// Desaparece
				atirado = false;
				bateu = true;
				deactivate();
			}
		}
	}

    public void Draw(GraphicsContext graphicsContext)
	{
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
    }    
}

