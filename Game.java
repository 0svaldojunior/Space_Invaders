import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Game
{
    private static Game game = null;
    private Nave nave;
    private List<Character> activeChars;

    private Game()
	{
      activeChars = new LinkedList<>();
    }

    public static Game getInstance()
	{
        if (game == null)
		{
            game = new Game();
        }
		
		return(game);
    }

    public void addChar(Character c)
	{
        activeChars.add(c);
        c.start();
    }

 	public void eliminate(Character c)
	{
        activeChars.remove(c);
		
		if (c == nave)
		{
            nave = null;
			Main.semCanvas();
			Main.showInput();
        }
    }

    public void Start()
	{
        // Repositório de personagens
        while(activeChars.size() > 0)
		{
          activeChars.remove(0);
        }
        // Adiciona o Nave
        nave = new Nave(400,550);
		
		activeChars.add(nave);
		
        if(Params.FASES == 1)
		{
			Params.Fase1();
		}//Eecutada so na primera vez que o jogo eh iniciado
		
		if(Params.FASES == 2)
		{
			Params.Fase2();
		}// loop de fases ate o Nave morrer
		
		if(Params.FASES == 3)
		{
			Params.Fase3();
		}//---
		
		if(Params.FASES == 4)
		{
			Params.Fase4();
		}// fim do loop;

        for(Character c:activeChars)
		{
            c.start();
		}
		
		if(Params.FASES <= 4)
		{
			Params.FASES++;}
		else
		{
            Params.FASES = 2;
            Params.AumentaDificult++;
			Params.AUMENTA_INIMIGOS++;
		}
    }
	
    public void Update(long currentTime, long deltaTime)
	{
        for(int i=0;i<activeChars.size();i++)
		{
            Character este = activeChars.get(i);
            este.Update();
			
            for(int j =0; j<activeChars.size();j++)
			{
                Character outro = activeChars.get(j);
				
				if ( este != outro)
				{
                    este.testaColisao(outro);
                }
            }
        }
		
         if(activeChars.size() == 1)
		 {
          Start();
		 }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed)
	{
        if (nave != null)
		{
            nave.OnInput(keyCode, isPressed);
        }
    }

    public void Draw(GraphicsContext graphicsContext)
	{
        for(Character c:activeChars)
		{
            c.Draw(graphicsContext);
        }
    }
}
