import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

/**
 * Represents the game Nace and Gun
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Nave extends BasicElement implements KeyboardCtrl
{
	private Image image;

    public Nave(int px,int py)
	{
        super(px,py);
		image = new Image(getClass().getResourceAsStream("Nave.png"));
		setSpeed( 15 );
    }

    @Override
    public void start()
	{
        setLimH(20,Params.WINDOW_WIDTH-20);
        setLimV(Params.WINDOW_HEIGHT-100,Params.WINDOW_HEIGHT);
        setLargAlt(40,50);
    }

    @Override
    public void Update()
	{
        if (jaColidiu())
		{
            deactivate();

        }
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed)
	{
        if (keyCode == KeyCode.LEFT)
		{
            if ( !(getX() < 0) ) setPosX(getX() - getSpeed());
        }
		
		if (keyCode == KeyCode.RIGHT)
		{
            if ( !(getX() >= getLMaxH() - getLargura()) ) setPosX(getX() + getSpeed());
        }
		
		if (keyCode == KeyCode.X || keyCode == KeyCode.SPACE && isPressed)
		{
            Game.getInstance().addChar(FactoryShot.getInstance().createInstance("Shot",getX()+12,getY()-47));
        }
	}

    @Override
    public void Draw(GraphicsContext graphicsContext)
	{
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
    }
}
