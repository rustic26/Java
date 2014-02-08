package rustic26.java.clustering.kmean;

import java.util.*;
import java.util.Map.Entry;

import rustic26.java.clustering.sample.BoundryType;
import rustic26.java.clustering.sample.ISample;
import rustic26.java.clustering.sample.Util;

public class Cluster<T extends ISample>
{
	public Cluster(int k)
	{
		_k = k;
		_set = new HashSet<T>();
		_map = new HashMap<T, Collection<T>>();
	}
	
	public void run()
	{
		
	}
	
	public int getK()
	{
		return _k;
	}
	
	private void groupAllSamples()
	{
		Iterable<T> prototypes = new ArrayList<T>(_map.keySet());
		_map.clear();
		
		for(T sample : _set)
		{
			T closestPrototype = Util.boundrySample(prototypes, sample, BoundryType.Closest);
			if(!_map.containsKey(closestPrototype))
			{
				_map.put(closestPrototype, new HashSet<T>());
			}
			_map.get(closestPrototype).add(sample);
		}
	}
	
	private void pickupPrototypes()
	{
		Map<T, Collection<T>> result = new HashMap<>();
		
		for(Collection<T> c : _map.values())
		{
			double min = Double.MAX_VALUE;
			T prototype = null;
			for(T t : c)
			{
				double dist = Util.TotalDist(c, t);
				if(min > dist)
				{
					min = dist;
					prototype = t;
				}
			}
			
			result.put(prototype, c);
		}
		_map = result;
	}
	
	private Map<T, Collection<T>> _map;
	
	private Set<T> _set;
	
	private int _k;
}
