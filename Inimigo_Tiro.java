import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * Represents the game Inimigo_Tiro and Gun
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Inimigo_Tiro extends BasicElement
{
    private int atira;
	private Image image;

    public Inimigo_Tiro(int px, int py)
	{
        super(px, py);
		image = Params.getInstance().getInimigo_Tiro();
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
    public void start()
	{
        setDirH(1);
		setDirV(10);
        setSpeed(3*Params.AumentaDificult);
        atira = 0;
		setLargAlt(60, 60);
    }

    @Override
    public void Update()
	{
        if (jaColidiu())
		{
			Params.CONT_RANK += 2;
			Params.VIEW_RANK.setText("" + Params.CONT_RANK);
            deactivate();
        }
		else
		{
			setPosX(getX() + getDirH() * getSpeed());
			// Se chegou no lado direito da tela ...
			
			if (getX() >= getLMaxH())
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
            // Logica do tiro
            atira++;
			
			if (atira == 80){
                Game.getInstance().addChar(FactoryShot.getInstance().createInstance("ShotDown",getX()+16,getY()+40));
                atira = 0;
            }
        }
    }

    public void Draw(GraphicsContext graphicsContext)
	{
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
    }
}
