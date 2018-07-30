import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * Represents the game Inimigo1 and your moviment
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Inimigo1 extends BasicElement
{
    private Image image;

    public Inimigo1(int px,int py)
	{
        super(px,py);
		image = Params.getInstance().getInimigo1();
    }

    @Override
    public void start()
	{
        setDirH(1);
        setSpeed(5*Params.AumentaDificult);
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
			Params.CONT_RANK++;
			Params.VIEW_RANK.setText("" + Params.CONT_RANK);
            deactivate();
        }
		else
		{
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
			
			if (getX() >= getLMaxH() - 40)
			{
                // Reposiciona no lado esquerdo e ...
                setPosX(getLMaxH() - 1);
                setDirH(-10);
                int oldY = getY();
                setPosY(oldY + 20);
            }
			else if (getX() <= getLMinH())
			{
                setPosX(getLMinH() + 1);
                setDirH(1);
                int oldY = getY();
                setPosY(oldY + 20);
            
            }
        }
    }
	
    public void Draw(GraphicsContext graphicsContext)
	{
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
    }
}
