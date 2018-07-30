import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import java.util.Random;
import javafx.scene.text.Text;

/**
 * Represents the Params game
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Params
{
	public static final String WINDOW_TITLE = "My Game V1.0";
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static int CONT_RANK = 0;
	public static int FASES = 1;
	public static int AumentaDificult = 1;
	public static int AUMENTA_INIMIGOS = 0;

	public static Text VIEW_RANK = new Text(0,0, "" + Params.CONT_RANK);;

	// Imagens
	private Image TIROC = new Image(getClass().getResourceAsStream("tiro_NAVE.png"));
	private Image INIMIGO1 = new Image(getClass().getResourceAsStream("Inimigo1.png"));
	private Image INIMIGO2 = new Image(getClass().getResourceAsStream("Inimigo2.png"));
	private Image INIMIGO3 = new Image(getClass().getResourceAsStream("Inimigo_Tiro.png"));
	private Image INIMIGO4 = new Image(getClass().getResourceAsStream("Boss.png"));
	private Image TIROI = new Image(getClass().getResourceAsStream("tiro_INIMIGO.png"));
	private Image ARMA = new Image(getClass().getResourceAsStream("Arma.png"));

	//public static Image CANHAO = new Image(getClass().getResourceAsStream("canhao.png"));

	private static Params params = null;
	private Random rnd;

	private Params()
	{
		rnd = new Random();
	}

	public static Params getInstance()
	{
		if (params == null)
		{
			params = new Params();
		}
		return(params);
	}

	public int nextInt(int lim)
	{
		return(rnd.nextInt(lim));
	}


	//Metodos que devolvem a imagem
	public Image getArma()
	{
		return(ARMA);
	}

	public Image getTiroNave()
	{
		return(TIROC);
	}

	public Image getInimigo1()
	{
		return(INIMIGO1);
	}

	public Image getInimigo2()
	{
		return(INIMIGO2);
	}

	public Image getInimigo_Tiro()
	{
		return(INIMIGO3);
	}

	public Image getBoss()
	{
		return(INIMIGO4);
	}

	public Image getTiroInimigo()
	{
		return(TIROI);
	}
	
	public static void Fase1()
	{
		for(int i = 0; i < (5 + Params.AUMENTA_INIMIGOS); i++)
		{
			Game.getInstance().addChar(Factory.getInstance().createInstance("Inimigo1",50+(i*40),50 + (i * 5)));
			Game.getInstance().addChar(Factory.getInstance().createInstance("Inimigo1",110+(i*40),200 + (i * 5)));
		}
	}
	public static void Fase2()
	{
		Fase1();
		for(int i = 0; i < Params.AUMENTA_INIMIGOS + 1; i++)
		{
			Game.getInstance().addChar(Factory.getInstance().createInstance("Inimigo_Composit",120+(i*150)+(i*55),30+(i*100)));
		}
	}
	
	public static void Fase3()
	{
		Fase2();
		for(int i = 0; i < (3 + Params.AUMENTA_INIMIGOS); i++)
		{
			Game.getInstance().addChar(Factory.getInstance().createInstance("Inimigo_Tiro",120+(i*100),270-(i*100)));
		}
	}
	
	public static void Fase4()
	{
		for(int i = 0; i < Params.AUMENTA_INIMIGOS + 1; i++)
		{
			Game.getInstance().addChar(Factory.getInstance().createInstance("Boss",150 + (i*100),50 - (i*50)));
		}
	}
}

























