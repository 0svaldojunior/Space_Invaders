import java.util.*;

/**
 * Represents the mode Factory Shot
 * @author Marco Antonio Junior, Osvaldo Junior feat (Niccolas, Bernado, Rafael Copstein, Bruno)
 */
public class FactoryShot
{
	private static FactoryShot instance = null;
	
	private FactoryShot(){}
	
	public static FactoryShot getInstance()
	{
		if(instance == null)
		{
			instance = new FactoryShot();
		}
		
		return instance;
	}
	
	public BasicElement createInstance(String Tipo,int px , int py)
	{
		if(Tipo.equalsIgnoreCase("Shot"))
		{
			return new Shot(px,py);
		}
		
		if(Tipo.equalsIgnoreCase("ShotDown"))
		{
			return new ShotDown(px,py);
		}
		
		if(Tipo.equalsIgnoreCase("ShotBoss"))
		{
			return new ShotBoss(px,py);
		}

		else
		{
			return (null);
		}
	}
}
