import java.util.*;

/**
 * Represents the mode Factory
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class Factory
{
	private static Factory instance = null;

  	private Factory(){}

    public static Factory getInstance()
	{
      	if(instance == null)
	  	{
        	instance = new Factory();
      	}
		
  		return instance;
    }

    public BasicElement createInstance(String Tipo,int px , int py)
	{
      	if(Tipo.equalsIgnoreCase("Inimigo1"))
	  	{
        	return(new Inimigo1(px,py));
      	}
		
		if(Tipo.equalsIgnoreCase("Inimigo_Tiro"))
		{
        	return(new Inimigo_Tiro(px,py));
      	}
		
		if(Tipo.equalsIgnoreCase("Inimigo_Composit"))
		{
        	return(new Inimigo_Composit(px,py));
      	}
		
      	if(Tipo.equalsIgnoreCase("Boss"))
		{
        	return(new Boss(px,py));
      	}
      	else
		{
			return (null);
		}
    }
}
