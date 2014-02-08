package rustic26.java.clustering.sample;

public class Util
{
	public static <T extends ISample> T boundrySample(
			Iterable<T> candidates, 
			T target, 
			BoundryType type)
	{
		double limit = type == BoundryType.Closest ? 
				Double.MAX_VALUE : Double.MIN_VALUE;
		T result = null;
		for(T t : candidates)
		{
			double dist = t.diff(target);
			switch(type)
			{
			case Closest:
				if(limit > dist)
				{
					limit = dist;
					result = t;
				}
				break;
				
			case Farest:
				if(limit < dist)
				{
					limit = dist;
					result = t;
				}
				break;
			}
		}
		
		return result;
	}

	public static <T extends ISample> double TotalDist(Iterable<T> samples, T target)
	{
		double sum = 0;
		for(T t : samples)
		{
			sum += t.diff(target);
		}
		return sum;
	}
}
