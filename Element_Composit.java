import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * Represents the game element on Inimigo_Composit
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Element_Composit extends BasicElement
{
	private Image image;
	
	public Element_Composit(int px,int py)
	{
		super(px,py);
		image = Params.getInstance().getArma();
	}
	
	@Override
	public void start()
	{
		setDirH(1);
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
		
			if (getX() >= getLMaxH())
			{
				// Reposiciona no lado esquerdo e ...
				setPosX(getLMinH());
				// Sorteia o passo de avanço [1,5]
				setSpeed(Params.getInstance().nextInt(5)+1);
			}
		}
	}
	
	public void Draw(GraphicsContext graphicsContext)
	{
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
	}
}

