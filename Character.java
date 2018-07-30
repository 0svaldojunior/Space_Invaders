import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;


/**
 * Represents the basic game character
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public interface Character
{
	/*@
	 @ ensures getX() >= 0;
	 @*/
	int getX();
	/*@
	 @ ensures getY() >= 0;
	 @*/
    int getY();
	/*@
	 @ ensures getAltura() >= 0;
	 @*/
    int getAltura();
	/*@
	 @ ensures getLargura() >= 0;
	 @*/
    int getLargura();

    /*@ pure @*/void testaColisao(Character c);
    /*@ pure @*/boolean jaColidiu();
    /*@ pure @*/void setColidiu();
    
    /*@ pure @*/void start();
    /*@ pure @*/boolean isActive();
    /*@ pure @*/void Update();
    /*@ pure @*/void Draw(GraphicsContext graphicsContext);
}
