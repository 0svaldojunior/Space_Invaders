import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.Character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * Handles window initialization and primary game setup
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */

public class Main extends Application
{
	private static Button gameBtn, quitBtn, rankBtn, rtnBtn, sbmBtn;
	private static Canvas gameCanvas, rankCanvas;
	private static VBox btns;
	private static HBox inputRank, viewPontis;
	private static int[] rank;
	private static String[] nomes;
	private static LinkedList lista;
	private static TextField nomeRank;
	private static Text nomeViewPontis;
	private static boolean setaCena = true;
	
	public static void habilitaBotao()
	{
		gameBtn.setVisible(!gameBtn.isVisible());
		quitBtn.setVisible(!quitBtn.isVisible());
		rankBtn.setVisible(!rankBtn.isVisible());
	}
	
	public static void habilitaVolta( boolean entra )
	{
	    if ( entra )
		{
			btns.setAlignment(Pos.BOTTOM_CENTER);
		}
	    else
		{
			btns.setAlignment(Pos.CENTER);
		}
	    rtnBtn.setVisible(!rtnBtn.isVisible());
		viewPontis.setVisible(false);
	}
	
	public static void semCanvas()
	{
	    gameCanvas.setVisible( false );
	    rankCanvas.setVisible( false );
	}
	
	public static void habilitaCanvas( boolean game )
	{
	    if(game)
		{
			gameCanvas.setVisible(!gameCanvas.isVisible());
			viewPontis.setVisible(!viewPontis.isVisible());
		}
	    else
		{
			rankCanvas.setVisible(!rankCanvas.isVisible());
			viewPontis.setVisible(false);
		}
	}
	
	public static void showInput()
	{
	    inputRank.setVisible(!inputRank.isVisible());
		viewPontis.setVisible(false);
	}
	
	//Carrega
	public static void lerArquivoTexto()
	{
        String linha = "";
        rank = new int[10]; 
        nomes = new String[10];       
        int contNum = 0, contNome = 0;
        lista = new LinkedList();
        try
		{
            FileReader arq = new FileReader("BD.txt");
            BufferedReader lerArq = new BufferedReader( arq );
            
            linha = lerArq.readLine();
            lerArq.close();
        }
		catch ( IOException e )
		{
            e.printStackTrace();
        }
        
        if ( linha == null )
			return;
        
        for( int i = 0; i < linha.length(); i++ )
		{
            if ( linha.charAt(i) == ' ' )
				continue;
			
			if ( contNum > rank.length || contNome > nomes.length )
				break;
			
			if ( Character.isDigit( linha.charAt(i) ) )
			{
                String num = "";
                num += linha.charAt(i++);
				
				while ( i < linha.length() && Character.isDigit( linha.charAt(i) ) )
				{
                    num += linha.charAt(i++);
                }
				
				rank[contNum++] = Integer.parseInt( num );
                continue;              
            }
			
			if ( Character.isLetter( linha.charAt(i) ) )
			{
                String nome = "";
                nome += linha.charAt(i++);
				
    			while( i < linha.length() && Character.isLetter( linha.charAt(i) ) )
				{
                    nome += linha.charAt(i++);                
                }
				
				nomes[contNome++] = nome;
                continue;
            }
        }
        
        for ( int i = 0; i < rank.length; i++ )
		{
            if ( rank[i] == 0 || nomes[i] == null )
				break;
			
			lista.insere( rank[i], nomes[i] );
        }
	}
	
	public static void sobreArquivoTexto( String nome, int points )
	{
       	try
		{
            FileWriter arq = new FileWriter("BD.txt");
            BufferedWriter gravarArq = new BufferedWriter( arq );
            lista.insere( points, nome );
            Iterator a = lista.getIterator();
            int contador = 0;
			
			while ( contador < 10 && a.hasNext() )
			{
                gravarArq.append( a.next() + " " );
                contador++;
            }
			
            gravarArq.close();
        }
		catch ( IOException e )
		{
            e.printStackTrace();
        }
	}
	
	public static void mudaCena()
	{
		setaCena = !setaCena;
	}
	
	public static void imprimeRankCanvas()
	{
        // O objeto principal do Canvas é o GraphicsContext, que usamos para desenhar
        GraphicsContext ctx = rankCanvas.getGraphicsContext2D();
        ctx.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        // estamos prontos para desenhar coisas! Vamos começar mudando a cor
        ctx.setFill(Color.RED);
        ctx.fillText("HighScores", Params.WINDOW_WIDTH / 2 - 40, 30);
        ctx.fillText("Rank", Params.WINDOW_WIDTH / 4 + 70, 55);
        ctx.fillText("Names", Params.WINDOW_WIDTH / 4 + 125, 55);
        ctx.fillText("Points", Params.WINDOW_WIDTH / 4 + 275, 55);

		for(int i = 0; i < 10; i++)
		{
			
			ctx.fillText(i+1 + "- ", (int)Params.WINDOW_WIDTH / 4 + 75, 30*i + 85);
			
			if ( rank[i] == 0 )
				continue;
			
			if ( nomes[i] == "" || nomes[i] == null )
				continue;
			
			if ( i + 1 == 10 )
				ctx.fillText(nomes[i], (int)Params.WINDOW_WIDTH / 4 + 135, 30*i + 85);
			
			else
				ctx.fillText(nomes[i], (int)Params.WINDOW_WIDTH / 4 + 125, 30*i + 85);
			
            ctx.fillText(""+rank[i], (int)Params.WINDOW_WIDTH / 4 + 275, 30*i + 85);
        }
    }
	
	@Override
	public void start(Stage stage) throws Exception
	{
		// Initialize Window
		stage.setTitle(Params.WINDOW_TITLE);
		//Group root = new Group();
		GridPane root = new GridPane();
		//root.setVisible(false);
		root.setAlignment(Pos.CENTER);
		//root.setGridLinesVisible( true );
		root.setPadding(new Insets(0, 0, 0, 0));
		
		String image = Main.class.getResource("gif2.gif").toExternalForm();
		
		root.setStyle("-fx-background-image: url('" + image + "'); "
					  + "-fx-background-size: 800 600;"
					  + "-fx-background-position: center center; "
					  + "-fx-background-repeat: stretch;");
		
		
        // Cria botões no menu princip al
        gameBtn = new Button("Start Game");
        quitBtn = new Button("Quit");
        rankBtn = new Button("HighScores");
        rtnBtn = new Button("Return Menu");
        sbmBtn = new Button("Send");
		
		viewPontis = new HBox(3);
		Label aux = new Label("Current Pontis: ");
		aux.setTextFill(Color.web("#FF0000"));
		Params.VIEW_RANK.setFill(Color.web("#FF0000"));
		viewPontis.getChildren().add(0, aux);
		viewPontis.getChildren().add(1, Params.VIEW_RANK);
		root.add(viewPontis, 0, 0);
		viewPontis.setAlignment(Pos.TOP_CENTER);
		viewPontis.setVisible(false);
		
        inputRank = new HBox(4);
        nomeRank = new TextField();
		Label aux2 =  new Label("Nome: ");
		aux2.setTextFill(Color.web("#FF0000"));
        inputRank.getChildren().add( 0, aux2);
        inputRank.getChildren().add( 1, nomeRank );
        inputRank.getChildren().add( 2, sbmBtn );
        root.add( inputRank, 0, 0 );
        inputRank.setAlignment(Pos.CENTER);
        inputRank.setVisible( false );        
        
        btns = new VBox(6);
        btns.getChildren().addAll(gameBtn, rankBtn, quitBtn, rtnBtn);
        rtnBtn.setVisible(false);
        //sbmBtn.setVisible(false);
        btns.setAlignment(Pos.CENTER);
        
        root.add( btns, 0, 0 );
		// Cria e posiciona o gameCanvas
		gameCanvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
		root.add(gameCanvas, 0, 0 );
		
		gameCanvas.setVisible(false);
		
		// Cria e posiciona o rankCanvas
		rankCanvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT - 45);
		root.add(rankCanvas, 0, 0);
		rankCanvas.setVisible(false);
		
		// Como quem nao quer nada
		Scene sceneJogo = new Scene( root, Color.BLUE );
		
		// Ações botões
		sbmBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				String nome = nomeRank.getText();
				
				if ( nome.length() > 0 )
				{
					lerArquivoTexto();
					sobreArquivoTexto( nome, Params.CONT_RANK );
				}
				
				showInput();
				habilitaBotao();
			}
		});
		
		rtnBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				habilitaCanvas(false);
				habilitaVolta(false);
				habilitaBotao();
			}
		});
		
		rankBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				lerArquivoTexto();
				habilitaVolta( true );
				habilitaCanvas( false );
				imprimeRankCanvas();
				habilitaBotao();
			}
		});
		
	    gameBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				Params.CONT_RANK = 0;
				Params.FASES = 1;
				habilitaCanvas( true );
				habilitaBotao();
				// Setup Game object
				Game.getInstance().Start();
			}
		});
		
		quitBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		// Register Game Loop
		final GraphicsContext gc = gameCanvas.getGraphicsContext2D();
		
		new AnimationTimer()
		{
			long lastNanoTime = System.nanoTime();
			
			@Override
			public void handle(long currentNanoTime)
			{
				long deltaTime = currentNanoTime - lastNanoTime;
				
				Game.getInstance().Update(currentNanoTime, deltaTime);
				gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
				Game.getInstance().Draw(gc);
				
				lastNanoTime = currentNanoTime;
			}
			
		}.start();
		
		// Show window
		stage.setScene( sceneJogo );
		
		// Register User Input Handler
		sceneJogo.setOnKeyPressed((KeyEvent event) ->
		{
			Game.getInstance().OnInput(event.getCode(), false);
		});
		
		sceneJogo.setOnKeyReleased((KeyEvent event) ->
		{
			Game.getInstance().OnInput(event.getCode(), true);
		});
		
		stage.show();
	}
	
	public static void main(String args[])
	{
		launch();
	}
}

