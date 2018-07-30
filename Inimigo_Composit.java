import java.util.*;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * Represents the game Inimigo_Composit and Gun
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Inimigo_Composit extends BasicElement
{
	private Image image;
    private List<BasicElement> elements;
	private int atira;

    public Inimigo_Composit(int px, int py)
	{
        super(px, py);
        elements = new ArrayList<BasicElement>();
		image = Params.getInstance().getInimigo2();
		atira = 0;
    }
	
    @Override
    public void start()
	{
        setDirH(1);
        setLargAlt(120, 72);
      
        elements.add(new Element_Composit(getX(), getY()));
        elements.add(new Element_Composit(getX() + 40, getY()));
        elements.add(new Element_Composit(getX() + 80, getY()));

        elements.add(new Element_Composit(getX(), getY() + 40));
        elements.add(new Element_Composit(getX() + 40, getY() + 40));
        elements.add(new Element_Composit(getX() + 80, getY() + 40));
		setSpeed(7*Params.AumentaDificult);
    }

    @Override
    public void testaColisao(Character outro)
	{
        for(int i=0;i<elements.size();i++)
		{
            elements.get(i).testaColisao(outro);
			
			if (elements.get(i).jaColidiu())
			{
                elements.remove(i);
				
				if (elements.size() == 0)
				{
					Params.CONT_RANK += 6;
					Params.VIEW_RANK.setText("" + Params.CONT_RANK);
                    deactivate();
                }
            }
        }
    }

    @Override
    public void Update()
	{
        setPosX(getX() + getDirH() * getSpeed());
		
		for (int i = 0; i < elements.size(); i++)
		{
            if (elements.get(i).jaColidiu())
			{
				Params.CONT_RANK += 6;
				Params.VIEW_RANK.setText("" + Params.CONT_RANK);
                elements.get(i).deactivate();
            }
			else
			{
                int auxX = elements.get(i).getX();
                elements.get(i).setPosX(auxX + getDirH() * getSpeed());
            }
        }
        if(getX() >= getLMaxH() - getLargura())
		{
            //setPosX(getLMaxH() - 1);
            int aux = getDirH();
            setDirH(aux * -1);
            int auxY = getY();
            setPosY(getY() + 20);
			
			for (int i = 0; i < elements.size(); i++)
			{
                auxY = elements.get(i).getY()+20;
                elements.get(i).setPosY(auxY);
            }
        }
		else if (getX() <= getLMinH())
		{
            //setPosX(getLMinH() + 1);
            int aux = getDirH();
            setDirH(aux * -1);
            int auxY = getY();
            setPosY(getY() + 20);
			
			for (int i = 0; i < elements.size(); i++)
			{
                auxY = elements.get(i).getY()+20;
                elements.get(i).setPosY(auxY);
            }
        }
		
		atira++;
		
		if (atira == 100){
			Game.getInstance().addChar(FactoryShot.getInstance().createInstance("ShotDown",getX()+1,getY()+40));
			Game.getInstance().addChar(FactoryShot.getInstance().createInstance("ShotDown",getX()+100,getY()+40));
			atira = 0;
		}
    }


    @Override
    public void Draw(GraphicsContext graphicsContext)
	{
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
		
		for(int i=0;i<elements.size();i++)
		{
            elements.get(i).Draw(graphicsContext);
        }
    }
}
